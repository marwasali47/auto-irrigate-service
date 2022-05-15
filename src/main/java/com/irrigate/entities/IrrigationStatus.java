package com.irrigate.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "irrigation_status")
@Data
public class IrrigationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "plot_id")
    private Long  plotId;

    @Column(name = "plot_name")
    private String plotName;

    @Column(name = "slot_id")
    private Long  slotId;

    @Column(name = "slot_name")
    private String slotName;

    @Column(name = "irrigation_time")
    private Long irrigationTime;

    @Column(name = "irrigation_status")
    private Boolean irrigationStatus;

    @Column(name = "water_amount")
    private Long waterAmount;

    @Column(name = "creation_date")
    private Long creationDate;


    @Override
    public String toString() {
        return  id + "";
    }
}
