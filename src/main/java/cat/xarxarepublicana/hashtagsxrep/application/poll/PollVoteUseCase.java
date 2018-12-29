package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

public class PollVoteUseCase {

    private final PollRepository pollRepository;

    public PollVoteUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public PollVoteResponse pollVote(String pollId, String proposalAuthorId, User voter) {
        Poll poll = pollRepository.findById(pollId);
        if (poll == null) {
            throw new EntityNotFoundException("No es pot votar. Enquesta no trobada " + pollId);
        }
        Proposal proposal = pollRepository.findProposal(poll, proposalAuthorId);
        if (proposal == null) {
            throw new EntityNotFoundException("No es pot votar. Proposta no trobada per l'enquesta " + pollId);
        }
        pollRepository.addVote(proposal, voter);
        return new PollVoteResponse(true);
    }

    public static class PollVoteResponse {
        private final Boolean saved;

        public PollVoteResponse(Boolean saved) {
            this.saved = saved;
        }

        public Boolean getSaved() {
            return saved;
        }
    }
}
