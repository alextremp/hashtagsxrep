package cat.xarxarepublicana.hashtagsxrep.application.monitor;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;

public class DeleteMonitorUseCase {

    private final MonitorRepository monitorRepository;
    private final TwitterExtractionRepository twitterExtractionRepository;

    public DeleteMonitorUseCase(MonitorRepository monitorRepository, TwitterExtractionRepository twitterExtractionRepository) {
        this.monitorRepository = monitorRepository;
        this.twitterExtractionRepository = twitterExtractionRepository;
    }

    public void deleteMonitor(String monitorId) {
        Monitor monitor = monitorRepository.findById(monitorId);
        if (monitor != null) {
            twitterExtractionRepository.deleteMonitorData(monitor);
            monitorRepository.delete(monitor);
        }
    }
}
