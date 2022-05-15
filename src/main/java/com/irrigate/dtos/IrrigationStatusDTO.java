package com.irrigate.dtos;

import lombok.Data;

import javax.persistence.*;


@Data
public class IrrigationStatusDTO {

    private Long  plotId;

    private String plotName;

    private Long  slotId;

    private String slotName;

    private Long irrigationTime;

    private Boolean irrigationStatus;

    private Long waterAmount;

    private Long creationDate;

}
