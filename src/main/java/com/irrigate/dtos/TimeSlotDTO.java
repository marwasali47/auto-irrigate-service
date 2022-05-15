package com.irrigate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDTO {

    @NotBlank
    private Long irrigationTime;

    @NotBlank
    private Long waterAmount;

}
