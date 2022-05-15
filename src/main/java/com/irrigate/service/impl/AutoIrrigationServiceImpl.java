package com.irrigate.service.impl;

import com.irrigate.entities.Plot;
import com.irrigate.entities.IrrigationStatus;
import com.irrigate.entities.TimeSlot;
import com.irrigate.repository.IrrigationStatusRepository;
import com.irrigate.repository.PlotRepository;
import com.irrigate.repository.SlotRepository;
import com.irrigate.repository.TimeSlotRepository;
import com.irrigate.requests.AlertSystemRequest;
import com.irrigate.requests.AutoIrrigateSensorRequest;
import com.irrigate.response.Response;
import com.irrigate.service.AutoIrrigationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class AutoIrrigationServiceImpl implements AutoIrrigationService {

    private Logger logger = LogManager.getLogger(AutoIrrigationServiceImpl.class);

    @Value("${sensor.api-url}")
    private String sensorURL;


    @Value("${alerting.api-url}")
    private String alertURL;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private IrrigationStatusRepository irrigationStatusRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RetryTemplate retryTemplate;

    @Override
    public void irrigatePlotSlot() {
        List<Plot> plots= plotRepository.findAll();
        plots.stream().forEach(plot -> {
            plot.getSlots().forEach(slot -> {
                slot.getTimeslots().stream().forEach(timeSlot -> {
                    Timer timer = new Timer();
                    TimerTask sensorTimeTask = new TimerTask() {
                        @Override
                        public void run() {

                            IrrigationStatus irrigationStatus = new IrrigationStatus();
                            irrigationStatus.setCreationDate(new Date().getTime());
                            irrigationStatus.setPlotId(plot.getId());
                            irrigationStatus.setPlotName(plot.getName());
                            irrigationStatus.setSlotId(slot.getId());
                            irrigationStatus.setSlotName(slot.getName());
                            irrigationStatus.setIrrigationStatus(false);
                            irrigationStatus.setWaterAmount(timeSlot.getWaterAmount());
                            irrigationStatus.setIrrigationTime(timeSlot.getIrrigationTime());
                            irrigationStatusRepository.save(irrigationStatus);
                            CallSenorAPI(timeSlot , irrigationStatus);
                            irrigationStatusRepository.save(irrigationStatus);
                        }
                    };
                    timer.schedule(sensorTimeTask, new Date(timeSlot.getIrrigationTime()));
                });
            });

        });
    }

    private void CallSenorAPI(TimeSlot timeSlot , IrrigationStatus irrigationStatus) {
        try {
            retryTemplate.execute(context -> {
                logger.info("Call Sensor API for " + timeSlot.getSlot().getName());
                AutoIrrigateSensorRequest request = new AutoIrrigateSensorRequest();
                request.setPlotId(timeSlot.getSlot().getPlot().getId());
                request.setSlotId(timeSlot.getSlot().getId());
                request.setWaterAmount(timeSlot.getWaterAmount());
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<AutoIrrigateSensorRequest> postHttpEntity = new HttpEntity<>(request, httpHeaders);
                ResponseEntity<Response>  response= restTemplate.postForEntity(URI.create(sensorURL), postHttpEntity, Response.class);
                if(response.getStatusCode() == HttpStatus.OK){
                    irrigationStatus.setIrrigationStatus(true);
                }
                return null;
            });

        } catch ( RestClientException e) {
            logger.error("after retry : call alert -->error for slot " + e.getMessage());
            retryTemplate.execute(context -> {
                logger.info("Call Alert API for " + timeSlot.getSlot().getName());
                AlertSystemRequest request = new AlertSystemRequest();
                request.setPlotId(timeSlot.getSlot().getPlot().getId());
                request.setSlotId(timeSlot.getSlot().getId());
                request.setIrrigationTime(timeSlot.getIrrigationTime());
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<AlertSystemRequest> postHttpEntity = new HttpEntity<>(request, httpHeaders);
                restTemplate.postForEntity(URI.create(alertURL), postHttpEntity, Response.class);
                return null;
            });
        }
    }
}
