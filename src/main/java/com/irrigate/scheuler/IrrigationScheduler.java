package com.irrigate.scheuler;


import com.irrigate.service.AutoIrrigationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IrrigationScheduler {

    private Logger logger = LogManager.getLogger(IrrigationScheduler.class);

    @Autowired
    private AutoIrrigationService autoIrrigationService ;

    // scheduled every 60 sec
    //@Scheduled(fixedRate = 60000)
    //Schdeuled 1 AM everyday
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleIrrigationTask() {
        logger.info("starting scheduled task at : " + new Date());
        autoIrrigationService.irrigatePlotSlot();
    }
}

