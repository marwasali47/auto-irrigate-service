package com.irrigate.response;

import com.irrigate.dtos.PlotDTO;
import lombok.Data;


@Data
public class PlotResponse extends Response {

    private PlotDTO plot;

    public PlotResponse(String message) {
        super(message);
    }
}
