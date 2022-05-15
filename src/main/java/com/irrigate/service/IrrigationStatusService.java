package com.irrigate.service;

import com.irrigate.dtos.IrrigationStatusDTO;

import java.util.List;
import java.util.Locale;

public interface IrrigationStatusService {

    List<IrrigationStatusDTO> listIrrigationStatus(Long startDate, Long endDate, Locale local);
}
