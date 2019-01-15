package cat.xarxarepublicana.hashtagsxrep.domain.extraction;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;

public interface TwitterExtractionRepository {
    boolean save(Monitor monitor, SearchTweetsResult searchTweetsResult, boolean ranked);

    void deleteMonitorData(Monitor monitor);
}
