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
}
