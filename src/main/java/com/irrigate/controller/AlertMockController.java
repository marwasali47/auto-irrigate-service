package com.irrigate.controller;

import com.irrigate.requests.AlertSystemRequest;
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
@RequestMapping(value = "/alerting")
public class AlertMockController {


    private Logger logger = LogManager.getLogger(AlertMockController.class);

    @PostMapping(value = { "/alert"})
    public ResponseEntity<Response> alert(@Valid @RequestBody AlertSystemRequest request , Locale locale){

        logger.info(" Alert is received");
        return new ResponseEntity<>(new Response("success"), HttpStatus.OK);
    }
}
