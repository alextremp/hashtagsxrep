package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.core.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

public class ModerationCloseUseCase {

  private final PollRepository pollRepository;

  public ModerationCloseUseCase(PollRepository pollRepository) {
    this.pollRepository = pollRepository;
  }

  public void closeModeration(
      String pollId,
      String proposalAuthorId,
      String hashtag,
      String subject,
      String cancelationReason,
      User moderator) {
    Proposal proposal = pollRepository.findProposal(pollId, proposalAuthorId);
    if (proposal == null) {
      throw new EntityNotFoundException("No s'ha trobat cap proposta");
    }
    proposal.update(hashtag, subject, cancelationReason, moderator.getNickname());
    pollRepository.saveProposal(proposal);
  }
}
