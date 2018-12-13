package cat.xarxarepublicana.hashtagsxrep.infrastructure.timer;

import cat.xarxarepublicana.hashtagsxrep.application.monitor.MonitorDataExtractionUseCase;
import org.springframework.scheduling.annotation.Scheduled;

public class MonitorTimer {

    private final MonitorDataExtractionUseCase monitorDataExtractionUseCase;

    public MonitorTimer(MonitorDataExtractionUseCase monitorDataExtractionUseCase) {
        this.monitorDataExtractionUseCase = monitorDataExtractionUseCase;
    }

    //@Scheduled(fixedDelayString = "${app.monitor.timer.delay}")
    public void onTime() {
        monitorDataExtractionUseCase.extractData();
    }
}
