package com.irrigate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTimeSlotDTO {

    private Long id ;

    @NotBlank(message = "irrigation.time.empty.error")
    private Long irrigationTime;

    @NotBlank(message = "water.amount.empty.error")
    private Long waterAmount;


}
