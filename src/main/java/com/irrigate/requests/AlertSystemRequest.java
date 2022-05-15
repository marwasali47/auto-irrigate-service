package com.irrigate.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertSystemRequest {

    @NotNull
    private Long plotId ;

    @NotNull
    private Long slotId ;

    @NotNull
    private Long irrigationTime;
}
