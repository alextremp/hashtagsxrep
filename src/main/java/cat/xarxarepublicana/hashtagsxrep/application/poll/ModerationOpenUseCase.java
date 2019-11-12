package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.core.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;

public class ModerationOpenUseCase {

  private final PollRepository pollRepository;

  public ModerationOpenUseCase(PollRepository pollRepository) {
    this.pollRepository = pollRepository;
  }

  public ModerationOpenUseCaseResponse loadProposal(String pollId, String proposalAuthorId) {
    Proposal proposal = pollRepository.findProposal(pollId, proposalAuthorId);
    if (proposal == null) {
      throw new EntityNotFoundException("No s'ha trobat cap proposta");
    }
    return new ModerationOpenUseCaseResponse(proposal);
  }

  public static class ModerationOpenUseCaseResponse {
    private final Proposal proposal;

    public ModerationOpenUseCaseResponse(Proposal proposal) {
      this.proposal = proposal;
    }

    public Proposal getProposal() {
      return proposal;
    }
  }
}
