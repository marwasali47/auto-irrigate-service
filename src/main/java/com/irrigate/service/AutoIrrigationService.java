package com.irrigate.service;

public interface AutoIrrigationService {

    //@Retryable( value = RestClientException.class , maxAttempts = 6)
    void irrigatePlotSlot();
}
