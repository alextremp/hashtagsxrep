package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;

public class LoadPollUseCase {

    private final PollRepository pollRepository;

    public LoadPollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public LoadPollResponse loadPoll (String pollId) {
        Poll poll = pollRepository.findById(pollId);
        return new LoadPollResponse(poll);
    }

    public static class LoadPollResponse {
        private final Poll poll;

        public LoadPollResponse(Poll poll) {
            this.poll = poll;
        }

        public Poll getPoll() {
            return poll;
        }
    }
}
