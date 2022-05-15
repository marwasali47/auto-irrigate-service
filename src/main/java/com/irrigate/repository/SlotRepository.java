package com.irrigate.repository;


import com.irrigate.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, String> {

    Slot findSlotByIdAndPlot_Id(Long slotId , Long plotId);
}
