package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionSaveException;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.Tweet;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.TwitterExtractionMapper;
import org.springframework.dao.DuplicateKeyException;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcTwitterExtractionRepository implements TwitterExtractionRepository {

    private static final Logger LOG = Logger.getLogger(JdbcTwitterExtractionRepository.class.getName());

    private final MonitorRepository monitorRepository;
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final TwitterExtractionFactory twitterExtractionFactory;
    private final TwitterExtractionMapper twitterExtractionMapper;


    public JdbcTwitterExtractionRepository(MonitorRepository monitorRepository, UserRepository userRepository, UserFactory userFactory, TwitterExtractionFactory twitterExtractionFactory, TwitterExtractionMapper twitterExtractionMapper) {
        this.monitorRepository = monitorRepository;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.twitterExtractionFactory = twitterExtractionFactory;
        this.twitterExtractionMapper = twitterExtractionMapper;
    }

    @Override
    public boolean save(Monitor monitor, SearchTweetsResult searchTweetsResult, boolean ranked) {
        Set<String> updatedUserIds = new HashSet<>();
        User user;
        TwitterExtraction twitterExtraction;
        boolean existingDataReached = false;
        for (Tweet tweet : searchTweetsResult.getStatuses()) {
            if (!updatedUserIds.contains(tweet.getUser().getIdStr())) {
                user = userFactory.createFromTwitterExtractedUser(tweet.getUser());
                userRepository.saveExtractedUser(user);
                updatedUserIds.add(user.getId());
            }
            twitterExtraction = twitterExtractionFactory.createFromMonitorExtractedTweet(monitor, tweet);
            try {
                twitterExtractionMapper.insert(twitterExtraction, ranked);
            } catch (Exception e) {
                if (DuplicateKeyException.class.isAssignableFrom(e.getClass())) {
                    if (LOG.isLoggable(Level.FINE)) {
                        LOG.log(Level.FINE, "Existing data reached: " + e.getMessage());
                    }
                    existingDataReached = true;
                    break;
                } else {
                    throw new TwitterExtractionSaveException("Error grabant extracció", e);
                }
            }
        }
        String nextQueryString = searchTweetsResult.getSearchMetadata().getNextResults();
        monitorRepository.updateCursor(monitor, existingDataReached ? null : nextQueryString);
        return existingDataReached;
    }

    @Override
    public void deleteMonitorData(Monitor monitor) {
        twitterExtractionMapper.deleteDataByMonitorId(monitor.getId());
    }
}
