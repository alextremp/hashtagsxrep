package cat.xarxarepublicana.hashtagsxrep.application.monitor;

import cat.xarxarepublicana.hashtagsxrep.domain.core.error.ValidationException;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import org.apache.commons.lang3.StringUtils;

public class DeleteMonitorUseCase {

  private final MonitorRepository monitorRepository;
  private final TwitterExtractionRepository twitterExtractionRepository;

  public DeleteMonitorUseCase(
      MonitorRepository monitorRepository,
      TwitterExtractionRepository twitterExtractionRepository) {
    this.monitorRepository = monitorRepository;
    this.twitterExtractionRepository = twitterExtractionRepository;
  }

  public void deleteMonitor(String monitorId, String hashtag) {
    Monitor monitor = monitorRepository.findById(monitorId);
    if (monitor != null) {
      if (StringUtils.equals(monitor.getTwitterQuery(), hashtag)) {
        twitterExtractionRepository.deleteMonitorData(monitor);
        monitorRepository.delete(monitor);
      } else {
        throw new ValidationException("No coincideixen: [" + monitor.getTwitterQuery() + "]/[" + hashtag + "]");
      }
    }
  }
}
