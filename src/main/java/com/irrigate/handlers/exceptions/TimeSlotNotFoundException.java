package com.irrigate.handlers.exceptions;

import org.springframework.http.HttpStatus;


public class TimeSlotNotFoundException extends ApiBaseException {

    public TimeSlotNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
