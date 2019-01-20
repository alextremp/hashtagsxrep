package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.poll.HashtagCountUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.controller.dto.HashtagCountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashtagController {

    private final HashtagCountUseCase hashtagCountUseCase;

    @Autowired
    public HashtagController(HashtagCountUseCase hashtagCountUseCase) {
        this.hashtagCountUseCase = hashtagCountUseCase;
    }

    @GetMapping("/hashtag/{hashtag}/count")
    public HashtagCountResponse hashtagCount(
            @PathVariable("hashtag") String hashtag
    ) {
        String error = hashtagCountUseCase.count("#" + hashtag);
        return error == null ? new HashtagCountResponse(true, null) : new HashtagCountResponse(false, error);
    }

}
