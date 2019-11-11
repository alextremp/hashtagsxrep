package cat.xarxarepublicana.hashtagsxrep.infrastructure.timer;

import cat.xarxarepublicana.hashtagsxrep.application.monitor.MonitorDataExtractionUseCase;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.logging.Logger;

public class MonitorTimer {

    private static final Logger LOG = Logger.getLogger(MonitorTimer.class.getName());

    private final MonitorDataExtractionUseCase monitorDataExtractionUseCase;

    public MonitorTimer(MonitorDataExtractionUseCase monitorDataExtractionUseCase) {
        this.monitorDataExtractionUseCase = monitorDataExtractionUseCase;
    }

    @Scheduled(fixedDelayString = "${app.monitor.timer.delay}", initialDelay = 60000)
    public void onTime() {
        LOG.info(">> onTime >> Monitor Timer :: START");
        monitorDataExtractionUseCase.extractData();
        LOG.info(">> onTime >> Monitor Timer :: END");
    }
}
