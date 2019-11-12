package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.poll.ValidateHashtagUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.controller.dto.HashtagCountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashtagController {

  private final ValidateHashtagUseCase validateHashtagUseCase;

  @Autowired
  public HashtagController(ValidateHashtagUseCase validateHashtagUseCase) {
    this.validateHashtagUseCase = validateHashtagUseCase;
  }

  @GetMapping("/hashtag/{hashtag}/count")
  public HashtagCountResponse hashtagCount(
      @PathVariable("hashtag") String hashtag
  ) {
    String error = validateHashtagUseCase.validate("#" + hashtag);
    return error == null ? new HashtagCountResponse(true, null) : new HashtagCountResponse(false, error);
  }
}
