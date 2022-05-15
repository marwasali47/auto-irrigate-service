package com.irrigate.controller;

import com.irrigate.requests.AutoIrrigateSensorRequest;
import com.irrigate.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping(value = "/sensor")
public class SensorMockController {


    private Logger logger = LogManager.getLogger(SensorMockController.class);

    @PostMapping(value = { "/irrigate"})
    public ResponseEntity<Response> irrigatePlotSection(@Valid @RequestBody AutoIrrigateSensorRequest request , Locale locale){

        return new ResponseEntity<>(new Response("success"), HttpStatus.OK);
    }
}
