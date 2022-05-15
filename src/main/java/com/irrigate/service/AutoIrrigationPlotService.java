package com.irrigate.service;

import com.irrigate.requests.AddPlotRequest;
import com.irrigate.requests.PatchPlotRequest;
import com.irrigate.response.PlotsResponse;
import com.irrigate.response.Response;

import java.util.Locale;

public interface AutoIrrigationPlotService {

    Response addPlot(AddPlotRequest plotRequest, Locale locale);

    Response patchPlot(Long id, PatchPlotRequest request, Locale locale);

    PlotsResponse listPlots(Locale locale);

}
