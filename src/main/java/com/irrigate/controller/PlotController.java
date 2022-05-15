package com.irrigate.controller;

import com.irrigate.requests.AddPlotRequest;
import com.irrigate.requests.PatchPlotRequest;
import com.irrigate.response.PlotsResponse;
import com.irrigate.response.Response;
import com.irrigate.service.AutoIrrigationPlotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping(value = "/irrigate")
public class PlotController {

    @Autowired
    private AutoIrrigationPlotService service;

    private Logger logger = LogManager.getLogger(PlotController.class);

    @PostMapping(value = { "/plot"})
    public ResponseEntity<Response> createPlot(@Valid @RequestBody AddPlotRequest request , Locale locale){

        return new ResponseEntity<>(service.addPlot(request,locale), HttpStatus.OK);
    }

    @PatchMapping(value = { "/plot/{id}"})
    public ResponseEntity <Response> patchPlot(@Valid @RequestBody PatchPlotRequest request, @PathVariable("id") Long id, Locale locale){

        return new ResponseEntity<>(service.patchPlot(id ,request,locale), HttpStatus.OK);
    }


    @GetMapping(value = { "/plot"})
    public ResponseEntity <PlotsResponse> listPlots(Locale locale){

        return new ResponseEntity<>(service.listPlots(locale), HttpStatus.OK);
    }
}
