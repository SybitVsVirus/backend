package de.wevsvirus.tasks;

import de.wevsvirus.facade.CallStatisticFacade;
import de.wevsvirus.facade.impl.RKIRiskFacade;
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

    @Resource
    private CallStatisticFacade callStatisticFacade;

    @Scheduled(fixedRate = 15 * 60 * 1000, initialDelay = 15 * 1000)
    public void updateRiskAreasInfo() {
        log.info("Trying to update risk areas");
        try {
            riskFacade.updateRiskAudio();
            log.info("Updated risk areas without error");
        } catch (Exception e) {
            log.warn("Failed to update risk areas audio", e);
        }
    }

    @Scheduled(fixedRate = 5 * 60 * 1000, initialDelay = 15 * 1000)
    public void saveStatistics() {
        log.info("Trying to save statistics");
        try {
            callStatisticFacade.saveStatistics();
            log.info("Updated statistics without error");
        } catch (Exception e) {
            log.warn("Failed to update statistics", e);
        }
    }
}
