package cat.xarxarepublicana.hashtagsxrep.application.monitor;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

public class MonitorDataExtractionUseCase {

  private static final Logger LOG = Logger.getLogger(MonitorDataExtractionUseCase.class.getName());

  private final Integer maxExtractionRequests;
  private final TwitterExtractionRepository twitterExtractionRepository;
  private final TwitterRepository twitterRepository;
  private final MonitorRepository monitorRepository;
  private final PollRepository pollRepository;

  public MonitorDataExtractionUseCase(
      Integer maxExtractionRequests,
      TwitterExtractionRepository twitterExtractionRepository,
      TwitterRepository twitterRepository,
      MonitorRepository monitorRepository,
      PollRepository pollRepository) {
    this.maxExtractionRequests = maxExtractionRequests;
    this.twitterExtractionRepository = twitterExtractionRepository;
    this.twitterRepository = twitterRepository;
    this.monitorRepository = monitorRepository;
    this.pollRepository = pollRepository;
  }

  public void extractData() {
    List<Monitor> monitorList = monitorRepository.getActiveMonitors();

    int extractionRequestsCounter = 0;
    boolean stopByBlockSize;
    boolean stopByWindowRequests = false;
    boolean existingDataReached;
    boolean ranked = false;
    StringBuffer queryString;
    SearchTweetsResult searchTweetsResult;

    Poll poll;
    LocalDateTime now;
    for (Monitor monitor : monitorList) {
      now = LocalDateTime.now();
      LOG.info(">> MONITOR: " + monitor.getId() + " >> " + monitor.getTwitterQuery());
      if (monitor.getActive() && now.isAfter(monitor.getEndDate())) {
        monitorRepository.disable(monitor.getId());
      } else {
        if (monitor.getAuthorId().equals("-1")) {
          poll = pollRepository.findById(monitor.getId());
          if (poll != null) {
            ranked = poll.getEndRankingTime() != null
                && now.isAfter(monitor.getCreationDate())
                && now.isBefore(poll.getEndRankingTime());
          }
        }
        stopByBlockSize = false;
        existingDataReached = false;
        while (!stopByBlockSize && !stopByWindowRequests && !existingDataReached) {
          queryString = new StringBuffer();
          if (StringUtils.isNotBlank(monitor.getNextQueryString())) {
            queryString.append(monitor.getNextQueryString());
          } else {
            String maxTweetId = monitorRepository.getMaxTweetId(monitor.getId());
            queryString.append("count=").append(TwitterRepository.MAX_STATUSES_PER_REQUEST);
            queryString.append("&q=").append(monitor.getTwitterQuery());
            if (StringUtils.isNotBlank(maxTweetId)) {
              queryString.append("&since_id=").append(maxTweetId);
            }
          }
          String query = queryString.toString();
          LOG.info(">> searchTweets: " + query);
          searchTweetsResult = twitterRepository.searchTweets(query);
          extractionRequestsCounter++;
          if (searchTweetsResult.getStatuses() != null) {
            LOG.log(
                Level.FINE,
                ">> RESULT: "
                    + searchTweetsResult.getStatuses().length
                    + " statuses >> NEXT: "
                    + searchTweetsResult.getSearchMetadata()
                    .getNextResults());
            existingDataReached = twitterExtractionRepository.save(monitor, searchTweetsResult, ranked);
            stopByBlockSize = searchTweetsResult.getStatuses().length < TwitterRepository.MAX_STATUSES_PER_REQUEST;
            if (stopByBlockSize) {
              LOG.info(">> STOP :: There are no more statuses yet");
            }
            stopByWindowRequests = extractionRequestsCounter >= maxExtractionRequests;
            if (stopByWindowRequests) {
              LOG.info(">> STOP :: Max extraction requests reached");
              return;
            }
          } else {
            LOG.warning(">> search result is null");
            stopByBlockSize = true;
          }
        }
      }
    }
  }
}
