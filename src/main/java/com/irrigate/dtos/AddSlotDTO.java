package com.irrigate.dtos;

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
@Valid
public class AddSlotDTO {

    private Long id;

    @NotBlank (message = "slot.name.blank.error")
    private String name ;

    @NotEmpty(message =  "time.slots.empty.error")
    private List<AddTimeSlotDTO> timeSlots;

}
