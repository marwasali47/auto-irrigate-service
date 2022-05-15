package com.irrigate.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoIrrigateSensorRequest {

    @NotNull
    private Long plotId;

    @NotNull
    private Long slotId;

    @NotNull
    private Long waterAmount;


}
