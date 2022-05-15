package com.irrigate.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "Time_Slot")
@Data
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "irrigation_time")
    private Long irrigationTime;

    @Column(name = "water_amount")
    private Long waterAmount;


    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @Override
    public String toString() {
        return  id + "";
    }
}
