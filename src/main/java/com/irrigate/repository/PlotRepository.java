package com.irrigate.repository;


import com.irrigate.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository<Plot, String> {

    Plot findPlotById(Long id);

}
