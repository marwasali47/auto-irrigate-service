package com.irrigate.response;

import com.irrigate.dtos.PlotDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class PlotsResponse {

    private List<PlotDTO> plots;

}
