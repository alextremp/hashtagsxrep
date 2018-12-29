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
        Proposal userProposal = pollRepository.findProposal(poll, user.getId());
        Proposal userVote = pollRepository.findUserVote(poll, user.getId());
        List<Proposal> proposalList = pollRepository.findPollProposals(poll);
        return new LoadPollResponse(poll, userProposal, userVote, proposalList);
    }

    public static class LoadPollResponse {
        private final Poll poll;
        private final Proposal userProposal;
        private final Proposal userVote;
        private final List<Proposal> pollProposals;

        public LoadPollResponse(Poll poll, Proposal userProposal, Proposal userVote, List<Proposal> pollProposals) {
            this.poll = poll;
            this.userProposal = userProposal;
            this.userVote = userVote;
            this.pollProposals = pollProposals;
        }

        public Poll getPoll() {
            return poll;
        }

        public Proposal getUserProposal() {
            return userProposal;
        }

        public Proposal getUserVote() {
            return userVote;
        }

        public List<Proposal> getPollProposals() {
            return pollProposals;
        }
    }
}
