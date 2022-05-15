package com.irrigate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDTO {

    private  Long id ;

    private String name ;

    private List<TimeSlotDTO> timeSlots;



}
