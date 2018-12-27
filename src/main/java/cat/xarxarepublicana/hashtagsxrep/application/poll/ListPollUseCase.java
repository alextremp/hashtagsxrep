package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;

import java.util.List;

public class ListPollUseCase {

    private final PollRepository pollRepository;

    public ListPollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public ListPollResponse listPoll() {
        List<Poll> pollList = pollRepository.findActive();
        return new ListPollResponse(pollList);
    }

    public static class ListPollResponse {
        private final List<Poll> pollList;

        public ListPollResponse(List<Poll> pollList) {
            this.pollList = pollList;
        }

        public List<Poll> getPollList() {
            return pollList;
        }
    }
}
