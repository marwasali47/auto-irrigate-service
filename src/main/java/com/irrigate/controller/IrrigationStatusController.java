package com.irrigate.controller;

import com.irrigate.dtos.IrrigationStatusDTO;
import com.irrigate.entities.IrrigationStatus;
import com.irrigate.response.PlotsResponse;
import com.irrigate.service.IrrigationStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/report")
public class IrrigationStatusController {

    private Logger logger = LogManager.getLogger(IrrigationStatusController.class);

    @Autowired
    private IrrigationStatusService service;

    @GetMapping(value = { "/status"})
    public ResponseEntity <List<IrrigationStatusDTO>> getTimeSlotsStatus(@RequestParam("startDate") Long startDate ,
                                                                         @RequestParam("endDate") Long endDate ,Locale locale){
        return new ResponseEntity<>(service.listIrrigationStatus(startDate , endDate ,locale), HttpStatus.OK);
    }
}
