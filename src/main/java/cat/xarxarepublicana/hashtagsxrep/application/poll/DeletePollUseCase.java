package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.core.error.ValidationException;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import org.apache.commons.lang3.StringUtils;

public class DeletePollUseCase {

  private final PollRepository pollRepository;

  public DeletePollUseCase(PollRepository pollRepository) {
    this.pollRepository = pollRepository;
  }

  public void deletePoll(String pollId, String descriptionValidator) {
    Poll poll = pollRepository.findById(pollId);
    if (poll != null) {
      if (StringUtils.equals(poll.getDescription(), descriptionValidator)) {
        pollRepository.delete(poll);
      } else {
        throw new ValidationException("No coincideixen: ["
                                          + poll.getDescription()
                                          + "]/["
                                          + descriptionValidator
                                          + "]");
      }
    }
  }
}
