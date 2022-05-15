package com.irrigate.repository;


import com.irrigate.entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, String> {

    TimeSlot findTimeSlotByIdAndSlot_Id(Long timeSlotId , Long slotId);
}
