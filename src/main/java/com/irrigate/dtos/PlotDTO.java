package com.irrigate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotDTO {

    private Long id ;

    private String name;

    private List<SlotDTO> slots;



}
