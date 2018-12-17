package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.time.LocalDateTime;
import java.util.UUID;

public class PollFactory {

    public Poll createPoll(String authorId, String authorNickname, String description, LocalDateTime startProposalsTime, LocalDateTime endProposalsTime, LocalDateTime endVotingTime) {
        return new Poll(
                UUID.randomUUID().toString(),
                authorId,
                authorNickname,
                description,
                LocalDateTime.now(),
                startProposalsTime,
                endProposalsTime,
                endVotingTime
        );
    }
}
