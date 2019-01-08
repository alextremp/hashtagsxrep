package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteGroup;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.ProposalFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

public class PollProposalUseCase {

    private final PollRepository pollRepository;
    private final ProposalFactory proposalFactory;
    private final InviteRepository inviteRepository;

    public PollProposalUseCase(PollRepository pollRepository, ProposalFactory proposalFactory, InviteRepository inviteRepository) {
        this.pollRepository = pollRepository;
        this.proposalFactory = proposalFactory;
        this.inviteRepository = inviteRepository;
    }

    public PollProposalResponse pollProposal(String pollId, User user, String hashtag, String subject) {
        if (pollRepository.hasProposal(pollId, user.getId())) {
            return new PollProposalResponse(false, "Ja tens una proposta guardada");
        }
        InviteGroup inviteGroup = inviteRepository.loadInvitesForPoll(pollId);
        if (!inviteGroup.isInvited(user)) {
            return new PollProposalResponse(false, "No pots proposar hashtags");
        }
        Proposal proposal = proposalFactory.create(pollId, user.getId(), user.getNickname(), hashtag, subject);
        pollRepository.addProposal(proposal);
        return new PollProposalResponse(true, null);
    }

    public static class PollProposalResponse {
        private final Boolean saved;
        private final String reason;

        public PollProposalResponse(boolean saved, String reason) {
            this.saved = saved;
            this.reason = reason;
        }

        public Boolean getSaved() {
            return saved;
        }

        public String getReason() {
            return reason;
        }
    }
}
