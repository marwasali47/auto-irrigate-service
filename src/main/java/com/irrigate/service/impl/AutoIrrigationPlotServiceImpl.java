package com.irrigate.service.impl;

import com.irrigate.dtos.*;
import com.irrigate.entities.Plot;
import com.irrigate.entities.Slot;
import com.irrigate.entities.TimeSlot;
import com.irrigate.handlers.exceptions.PlotNotFoundException;
import com.irrigate.handlers.exceptions.SlotNotFoundException;
import com.irrigate.handlers.exceptions.TimeSlotNotFoundException;
import com.irrigate.repository.PlotRepository;
import com.irrigate.repository.SlotRepository;
import com.irrigate.repository.TimeSlotRepository;
import com.irrigate.requests.AddPlotRequest;
import com.irrigate.requests.PatchPlotRequest;
import com.irrigate.response.PlotsResponse;
import com.irrigate.response.Response;
import com.irrigate.service.AutoIrrigationPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AutoIrrigationPlotServiceImpl implements AutoIrrigationPlotService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private SlotRepository slotRepository;


    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public Response addPlot(AddPlotRequest request, Locale locale) {

        Plot plot = new Plot();
        plot.setName(request.getName());

        List<Slot> slots = request.getSlots().stream()
                .map(slotDTO -> toSlot(slotDTO , plot))
                .collect(Collectors.toList());

        plot.setSlots(slots);
        plotRepository.save(plot);

        return new Response(messageSource.getMessage("add.plot.success", null,locale));
    }

    @Override
    public Response patchPlot(Long plotId, PatchPlotRequest request, Locale locale) {

        Plot plot = plotRepository.findPlotById(plotId);

        if(plot == null)
            throw new PlotNotFoundException(messageSource.getMessage("plot.not.found.error", null, locale));

        switch(request.getOpName()) {
            case "ADD":
                addSlotsToPlots(request.getSlots() , plot ,locale);
                break;
            case "REPLACE":
                replaceSlotsToPlots(request.getSlots() , plot , locale);
                break;
            default:
                // code block
        }
        return new Response(messageSource.getMessage("patch.plot.success", null,locale));
    }

    @Override
    public PlotsResponse listPlots(Locale locale) {

        List<Plot> plots = plotRepository.findAll();

        List<PlotDTO> plotDTOs = plots.stream()
                .map(plot -> toPlotDTO(plot))
                .collect(Collectors.toList());

        PlotsResponse plotResponse = new PlotsResponse();
        plotResponse.setPlots(plotDTOs);
        return plotResponse;
    }

    private void removeSlotsFromPlots(List<AddSlotDTO> slots, Plot plot, Locale locale) {
        slots.stream().forEach(slotDTO -> {
            Slot slot = slotRepository.findSlotByIdAndPlot_Id(slotDTO.getId() , plot.getId());

            if(slot == null)
                throw new SlotNotFoundException(messageSource.getMessage("slot.not.found.error", new Object[]{slotDTO.getId() + ""}, locale));
           plot.getSlots().remove(slot);
           plotRepository.save(plot);
        });
    }

    private void replaceSlotsToPlots(List<AddSlotDTO> slots, Plot plot, Locale locale) {

        slots.stream().forEach(slotDTO -> {

            Slot slot = slotRepository.findSlotByIdAndPlot_Id(slotDTO.getId() , plot.getId());

            if(slot == null)
                throw new SlotNotFoundException(messageSource.getMessage("slot.not.found.error", new Object[]{slotDTO.getId()}, locale));

            if(!StringUtils.isEmpty(slotDTO.getName())){
                slot.setName(slotDTO.getName());
                slotRepository.save(slot);
            }

            slotDTO.getTimeSlots().stream().forEach(timeSlotDTO -> {
                TimeSlot timeSlot = timeSlotRepository.findTimeSlotByIdAndSlot_Id(timeSlotDTO.getId() , slot.getId());
                if(timeSlot == null)
                    throw new TimeSlotNotFoundException(messageSource.getMessage("timeSlot.not.found.error", new Object[]{slotDTO.getId()}, locale));

                if(!StringUtils.isEmpty(timeSlotDTO.getIrrigationTime()))
                    timeSlot.setIrrigationTime(timeSlotDTO.getIrrigationTime());

                if(!StringUtils.isEmpty(timeSlotDTO.getWaterAmount()))
                    timeSlot.setWaterAmount(timeSlotDTO.getWaterAmount());
                timeSlotRepository.save(timeSlot);
            });
        });
    }


    private void addSlotsToPlots(List<AddSlotDTO> slotDTOs, Plot plot, Locale locale) {
        List<Slot> slots =slotDTOs.stream()
                .map(slotDTO -> toSlot(slotDTO , plot))
                .collect(Collectors.toList());

        plot.getSlots().addAll(slots);
        plotRepository.save(plot);
    }

    private PlotDTO toPlotDTO(Plot plot) {

        PlotDTO plotDTO = new PlotDTO();
        plotDTO.setName(plot.getName());
        plotDTO.setId(plot.getId());
        List<SlotDTO> slotDTOS = plot.getSlots().stream()
                .map(slot -> toSlotDTO(slot))
                .collect(Collectors.toList());
        plotDTO.setSlots(slotDTOS);
        return plotDTO;
    }

    private Slot toSlot(AddSlotDTO slotDTO, Plot plot){

        Slot slot = new Slot();
        slot.setName(slotDTO.getName());
        slot.setPlot(plot);

        List<TimeSlot> timeSlotList = slotDTO.getTimeSlots()
                .stream()
                .map(addTimeSlotDTO ->  toTimeSlot(addTimeSlotDTO , slot))
                .collect(Collectors.toList());

        slot.setTimeslots(timeSlotList);
        return slot;
    }

    private TimeSlot toTimeSlot(AddTimeSlotDTO addTimeSlotDTO, Slot slot) {

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setSlot(slot);
        timeSlot.setIrrigationTime(addTimeSlotDTO.getIrrigationTime());
        timeSlot.setWaterAmount(addTimeSlotDTO.getWaterAmount());
        return timeSlot;

    }

    private SlotDTO toSlotDTO(Slot slot){

        SlotDTO slotDTO = new SlotDTO();
        slotDTO.setName(slot.getName());

        List<TimeSlotDTO> timeSlotDTOs = slot.getTimeslots()
                .stream()
                .map(timeSlot -> toTimeSlotDTO(timeSlot))
                .collect(Collectors.toList());

        slotDTO.setTimeSlots(timeSlotDTOs);
        slotDTO.setId(slot.getId());
        return slotDTO;
    }

    private TimeSlotDTO toTimeSlotDTO(TimeSlot timeSlot) {
        TimeSlotDTO timeSlotDTO = new TimeSlotDTO();
        timeSlotDTO.setIrrigationTime(timeSlot.getIrrigationTime());
        timeSlotDTO.setWaterAmount(timeSlot.getWaterAmount());
        return timeSlotDTO;
    }

}
