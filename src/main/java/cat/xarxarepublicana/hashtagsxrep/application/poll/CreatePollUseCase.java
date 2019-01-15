package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.time.LocalDateTime;

public class CreatePollUseCase {

    private final PollFactory pollFactory;
    private final PollRepository pollRepository;
    private final InviteRepository inviteRepository;

    public CreatePollUseCase(PollFactory pollFactory, PollRepository pollRepository, InviteRepository inviteRepository) {
        this.pollFactory = pollFactory;
        this.pollRepository = pollRepository;
        this.inviteRepository = inviteRepository;
    }

    public CreatePollResponse createPoll(User author, String description, LocalDateTime startProposalsTime, LocalDateTime endProposalsTime, LocalDateTime endVotingTime, LocalDateTime startEventTime, LocalDateTime endRankingTime) {
        Poll poll = pollFactory.createPoll(author.getId(), author.getNickname(), description, startProposalsTime, endProposalsTime, endVotingTime, startEventTime, endRankingTime);
        pollRepository.save(poll);
        inviteRepository.inviteToPoll(poll.getId());
        return new CreatePollResponse(poll);
    }

    public static class CreatePollResponse {
        private final Poll poll;

        public CreatePollResponse(Poll poll) {
            this.poll = poll;
        }

        public Poll getPoll() {
            return poll;
        }
    }
}
