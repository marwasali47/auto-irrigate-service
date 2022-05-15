package com.irrigate.handlers.exceptions;

import org.springframework.http.HttpStatus;


public class PlotNotFoundException extends ApiBaseException {

    public PlotNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
