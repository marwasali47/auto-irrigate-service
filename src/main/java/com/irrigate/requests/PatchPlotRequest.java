package com.irrigate.requests;

import com.irrigate.dtos.AddSlotDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchPlotRequest {

    private String OpName;

    @Valid
    private List<AddSlotDTO> slots;

}
