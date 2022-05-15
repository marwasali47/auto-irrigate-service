package com.irrigate.repository;


import com.irrigate.entities.IrrigationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IrrigationStatusRepository extends JpaRepository<IrrigationStatus, String> {

    List<IrrigationStatus> findAllByCreationDateGreaterThanEqualAndCreationDateLessThanEqual(Long creationDate , Long endDate);

}
