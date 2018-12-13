package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.Tweet;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.TwitterExtractionMapper;

import java.util.HashSet;
import java.util.Set;

public class JdbcTwitterExtractionRepository implements TwitterExtractionRepository {

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
    public void save(Monitor monitor, SearchTweetsResult searchTweetsResult) {
        Set<String> updatedUserIds = new HashSet<>();
        User user;
        TwitterExtraction twitterExtraction;
        monitorRepository.updateCursor(monitor, searchTweetsResult.getSearchMetadata().getMaxIdStr());
        for (Tweet tweet: searchTweetsResult.getStatuses()) {
            if (!updatedUserIds.contains(tweet.getUser().getIdStr())) {
                user = userFactory.createFromTwitterExtractedUser(tweet.getUser());
                userRepository.saveExtractedUser(user);
                updatedUserIds.add(user.getId());
            }
            twitterExtraction = twitterExtractionFactory.createFromMonitorExtractedTweet(monitor, tweet);
            twitterExtractionMapper.insert(twitterExtraction);
        }
    }
}
