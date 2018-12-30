package cat.xarxarepublicana.hashtagsxrep.domain.monitor;

import java.time.LocalDateTime;
import java.util.UUID;

public class MonitorFactory {

    public Monitor createNewMonitor(String authorId, String authorNickname, String twitterQuery, LocalDateTime startDate) {
        return new Monitor(
                UUID.randomUUID().toString(),
                authorId,
                authorNickname,
                true,
                twitterQuery,
                LocalDateTime.now(),
                startDate,
                null,
                null
        );
    }

    public Monitor createMonitorFromPoll(String pollId, String hashtag, LocalDateTime startDate) {
        return new Monitor(
                pollId,
                "-1",
                "auto",
                true,
                hashtag,
                LocalDateTime.now(),
                startDate,
                null,
                null
        );
    }
}
