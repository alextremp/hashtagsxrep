package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteGroup;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.util.List;

public class LoadPollUseCase {

    private final PollRepository pollRepository;
    private final InviteRepository inviteRepository;

    public LoadPollUseCase(PollRepository pollRepository, InviteRepository inviteRepository) {
        this.pollRepository = pollRepository;
        this.inviteRepository = inviteRepository;
    }

    public LoadPollResponse loadPoll (String pollId, User user) {
        Poll poll = pollRepository.findById(pollId);
        if (poll == null) {
            throw new EntityNotFoundException("Enquesta no trobada: " + pollId);
        }
        Proposal userProposal = pollRepository.findProposal(poll.getId(), user.getId());
        Proposal userVote = pollRepository.findUserVote(poll, user.getId());
        List<Proposal> proposalList = pollRepository.findPollProposals(poll);
        InviteGroup inviteGroup = inviteRepository.loadInvitesForPoll(poll.getId());
        return new LoadPollResponse(poll, userProposal, userVote, proposalList, inviteGroup);
    }

    public static class LoadPollResponse {
        private final Poll poll;
        private final Proposal userProposal;
        private final Proposal userVote;
        private final List<Proposal> pollProposals;
        private final InviteGroup inviteGroup;

        public LoadPollResponse(Poll poll, Proposal userProposal, Proposal userVote, List<Proposal> pollProposals, InviteGroup inviteGroup) {
            this.poll = poll;
            this.userProposal = userProposal;
            this.userVote = userVote;
            this.pollProposals = pollProposals;
            this.inviteGroup = inviteGroup;
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

        public InviteGroup getInviteGroup() {
            return inviteGroup;
        }

        public Integer getPollVoteCount() {
            if (getPollProposals() == null) {
                return 0;
            }
            int count = 0;
            for (Proposal proposal: getPollProposals()) {
                count += proposal.getVotes();
            }
            return count;
        }
    }
}
