package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.time.LocalDateTime;

public class ProposalFactory {

  public Proposal create(String pollId, String authorId, String authorNickname, String hashtag, String subject) {
    return new Proposal(
        pollId,
        authorId,
        authorNickname,
        hashtag,
        subject,
        null,
        null,
        LocalDateTime.now(),
        0);
  }
}
