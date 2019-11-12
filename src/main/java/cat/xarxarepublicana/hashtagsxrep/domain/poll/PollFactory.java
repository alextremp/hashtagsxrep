package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.time.LocalDateTime;
import java.util.UUID;

public class PollFactory {

  public Poll createPoll(
      String authorId,
      String authorNickname,
      String description,
      LocalDateTime startProposalsTime,
      LocalDateTime endProposalsTime,
      LocalDateTime endVotingTime,
      LocalDateTime startEventTime,
      LocalDateTime endRankingTime) {
    return new Poll(
        UUID.randomUUID().toString(),
        authorId,
        authorNickname,
        description,
        null,
        LocalDateTime.now(),
        startProposalsTime,
        endProposalsTime,
        endVotingTime,
        startEventTime,
        endRankingTime);
  }
}
