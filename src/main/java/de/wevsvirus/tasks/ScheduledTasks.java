package de.wevsvirus.tasks;

import de.wevsvirus.facades.RKIRiskFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Resource
    private RKIRiskFacade riskFacade;

    @Scheduled(fixedRate = 15*60*1000, initialDelay = 15*1000)
    public void updateRiskAreasInfo() {
        log.info("Trying to update risk areas");
        try {
            riskFacade.updateRiskAudio();
            log.info("Updated risk areas without error");
        } catch (Exception e) {
            log.warn("Failed to update risk areas audio", e);
        }
    }
}
