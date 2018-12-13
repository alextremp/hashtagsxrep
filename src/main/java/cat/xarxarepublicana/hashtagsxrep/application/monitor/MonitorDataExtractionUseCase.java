package cat.xarxarepublicana.hashtagsxrep.application.monitor;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import org.apache.commons.lang3.StringUtils;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.logging.Logger;

public class MonitorDataExtractionUseCase {

    private static final Logger LOG = Logger.getLogger(MonitorDataExtractionUseCase.class.getName());

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
        boolean existingDataReached = false;
        StringBuffer queryString;
        SearchTweetsResult searchTweetsResult;

        for (Monitor monitor : monitorList) {
            LOG.info(">> MONITOR: " + monitor.getId() + " >> " + monitor.getTwitterQuery());
            stopByBlockSize = false;
            existingDataReached = false;
            while (!stopByBlockSize && !stopByWindowRequests && !existingDataReached) {
                queryString = new StringBuffer();
                if (StringUtils.isNotBlank(monitor.getNextQueryString())) {
                    queryString.append(monitor.getNextQueryString());
                } else {
                    String maxTweetId = monitorRepository.getMaxTweetId(monitor.getId());
                    queryString.append("q=").append(monitor.getTwitterQuery());
                    queryString.append("&count=").append(TwitterRepository.MAX_STATUSES_PER_REQUEST);
                    if (StringUtils.isNotBlank(maxTweetId)) {
                        queryString.append("&since_id=").append(maxTweetId);
                    }
                }
                searchTweetsResult = twitterRepository.searchTweets(queryString.toString());

                LOG.info(">> RESULT: " + searchTweetsResult.getStatuses().length + " statuses >> NEXT: " + searchTweetsResult.getSearchMetadata().getNextResults());
                existingDataReached = twitterExtractionRepository.save(monitor, searchTweetsResult);
                stopByBlockSize = searchTweetsResult.getStatuses().length < TwitterRepository.MAX_STATUSES_PER_REQUEST;
                if (stopByBlockSize) {
                    LOG.info(">> STOP as there are no more statuses yet");
                }
                extractionRequestsCounter++;
                stopByWindowRequests = extractionRequestsCounter >= maxExtractionRequests;
                if (stopByWindowRequests) {
                    LOG.info(">> STOP :: Max extraction requests reached");
                    return;
                }
            }
        }
    }
}
