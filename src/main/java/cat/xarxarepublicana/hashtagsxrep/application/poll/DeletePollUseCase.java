package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;

public class DeletePollUseCase {

    private final PollRepository pollRepository;

    public DeletePollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public void deletePoll(String id) {
        Poll poll = pollRepository.findById(id);
        if (poll != null) {
            pollRepository.delete(poll);
        }
    }
}
