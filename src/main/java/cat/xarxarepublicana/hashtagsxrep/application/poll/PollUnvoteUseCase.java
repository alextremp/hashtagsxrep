package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

public class PollUnvoteUseCase {

    private final PollRepository pollRepository;

    public PollUnvoteUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public void pollUnvote(String pollId, User user) {
        pollRepository.deleteVote(pollId, user.getId());
    }

}
