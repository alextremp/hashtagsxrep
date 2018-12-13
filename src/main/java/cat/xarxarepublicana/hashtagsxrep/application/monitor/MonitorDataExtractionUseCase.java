package cat.xarxarepublicana.hashtagsxrep.application.monitor;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;

import java.util.List;

public class MonitorDataExtractionUseCase {

    private final Integer maxExtractionRequests;
    private final TwitterExtractionRepository twitterExtractionRepository;
    private final TwitterRepository twitterRepository;
    private final MonitorRepository monitorRepository;

    public MonitorDataExtractionUseCase(Integer maxExtractionRequests, TwitterExtractionRepository twitterExtractionRepository, TwitterRepository twitterRepository, MonitorRepository monitorRepository) {
        this.maxExtractionRequests = maxExtractionRequests;
        this.twitterExtractionRepository = twitterExtractionRepository;
        this.twitterRepository = twitterRepository;
        this.monitorRepository = monitorRepository;
    }

    public void extractData() {
        List<Monitor> monitorList = monitorRepository.getActiveMonitors();

        int extractionRequestsCounter = 0;
        boolean stopByBlockSize;
        boolean stopByWindowRequests = false;
        SearchTweetsResult searchTweetsResult;

        for (Monitor monitor : monitorList) {
            stopByBlockSize = false;
            while (!stopByBlockSize && !stopByWindowRequests) {
                searchTweetsResult = twitterRepository.searchTweets(monitor.getTwitterQuery(), monitor.getStartDate(), monitor.getLastUpdateCursor());
                twitterExtractionRepository.save(monitor, searchTweetsResult);
                stopByBlockSize = searchTweetsResult.getStatuses().length < twitterRepository.getStatusesBlockSize();
            }
            extractionRequestsCounter++;
            stopByWindowRequests = extractionRequestsCounter >= maxExtractionRequests;
            if (stopByWindowRequests) {
                break;
            }
        }
    }
}
