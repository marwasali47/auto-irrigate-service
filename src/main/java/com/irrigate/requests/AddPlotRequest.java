package com.irrigate.requests;

import com.irrigate.dtos.AddSlotDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPlotRequest {

    @NotBlank(message = "plot.name.blank.error")
    private String name;

    @NotEmpty(message= "slots.empty.error")
    @Valid
    private List<AddSlotDTO> slots;

}
