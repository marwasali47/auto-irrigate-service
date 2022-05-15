package com.irrigate.handlers.exceptions;

import org.springframework.http.HttpStatus;


public class SlotNotFoundException extends ApiBaseException {

    public SlotNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
