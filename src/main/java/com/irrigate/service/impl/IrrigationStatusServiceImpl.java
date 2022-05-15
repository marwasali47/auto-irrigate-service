package com.irrigate.service.impl;

import com.irrigate.dtos.IrrigationStatusDTO;
import com.irrigate.entities.IrrigationStatus;
import com.irrigate.repository.IrrigationStatusRepository;
import com.irrigate.service.IrrigationStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class IrrigationStatusServiceImpl implements IrrigationStatusService {

    private Logger logger = LogManager.getLogger(IrrigationStatusServiceImpl.class);


    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IrrigationStatusRepository irrigationStatusRepository;


    @Override
    public List<IrrigationStatusDTO> listIrrigationStatus(Long startDate, Long endDate, Locale local) {
        List<IrrigationStatus> irrigationStatusList = irrigationStatusRepository
                    .findAllByCreationDateGreaterThanEqualAndCreationDateLessThanEqual(startDate , endDate);
        List<IrrigationStatusDTO> irrigationStatusDTOS = irrigationStatusList
                .stream()
                .map(irrigationStatus -> toIrrigationStatusDTO(irrigationStatus))
                .collect(Collectors.toList());

        return irrigationStatusDTOS;
    }

    private IrrigationStatusDTO toIrrigationStatusDTO(IrrigationStatus irrigationStatus) {
        IrrigationStatusDTO dto = new IrrigationStatusDTO();
        dto.setPlotId(irrigationStatus.getPlotId());
        dto.setSlotId(irrigationStatus.getSlotId());
        dto.setIrrigationStatus(irrigationStatus.getIrrigationStatus());
        dto.setIrrigationTime(irrigationStatus.getIrrigationTime());
        dto.setCreationDate(irrigationStatus.getCreationDate());
        dto.setPlotName(irrigationStatus.getPlotName());
        dto.setSlotName(irrigationStatus.getSlotName());
        dto.setWaterAmount(irrigationStatus.getWaterAmount());
        return dto;

    }
}
