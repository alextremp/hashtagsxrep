package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.util.List;

public class LoadPollUseCase {

    private final PollRepository pollRepository;

    public LoadPollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public LoadPollResponse loadPoll (String pollId, User user) {
        Poll poll = pollRepository.findById(pollId);
        if (poll == null) {
            throw new EntityNotFoundException("Enquesta no trobada: " + pollId);
        }
        Proposal userProposal = pollRepository.findProposal(pollId, user.getId());

        List<Proposal> proposalList = null;
        if (poll.isVotingTime()) {
            proposalList = pollRepository.findPollProposals(pollId);
        }
        return new LoadPollResponse(poll, userProposal, proposalList);
    }

    public static class LoadPollResponse {
        private final Poll poll;
        private final Proposal userProposal;
        private final List<Proposal> pollProposals;

        public LoadPollResponse(Poll poll, Proposal userProposal, List<Proposal> pollProposals) {
            this.poll = poll;
            this.userProposal = userProposal;
            this.pollProposals = pollProposals;
        }

        public Poll getPoll() {
            return poll;
        }

        public Proposal getUserProposal() {
            return userProposal;
        }

        public List<Proposal> getPollProposals() {
            return pollProposals;
        }
    }
}
