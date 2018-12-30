package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.time.LocalDateTime;

public class CreatePollUseCase {

    private final PollFactory pollFactory;
    private final PollRepository pollRepository;

    public CreatePollUseCase(PollFactory pollFactory, PollRepository pollRepository) {
        this.pollFactory = pollFactory;
        this.pollRepository = pollRepository;
    }

    public CreatePollResponse createPoll(User author, String description, LocalDateTime startProposalsTime, LocalDateTime endProposalsTime, LocalDateTime endVotingTime, LocalDateTime startEventTime) {
        Poll poll = pollFactory.createPoll(author.getId(), author.getNickname(), description, startProposalsTime, endProposalsTime, endVotingTime, startEventTime);
        pollRepository.save(poll);
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
