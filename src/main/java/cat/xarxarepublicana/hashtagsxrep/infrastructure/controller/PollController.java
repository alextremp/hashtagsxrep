package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.poll.CreatePollUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PollController {

    private final CreatePollUseCase createPollUseCase;

    @Autowired
    public PollController(CreatePollUseCase createPollUseCase) {
        this.createPollUseCase = createPollUseCase;
    }

    @GetMapping(Views.URL_POLL)
    @Secured("ROLE_TAGGER")
    public String listPolls() {
        return Views.VIEW_POLL;
    }

    @PostMapping(Views.URL_POLL)
    @Secured("ROLE_CREATOR")
    public String createPoll(
            @RequestParam("description")
                    String description,
            @RequestParam("startProposalsTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime startProposalsTime,
            @RequestParam("endProposalsTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime endProposalsTime,
            @RequestParam("endVotingTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime endVotingTime,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser,
            Model model
    ) {
        CreatePollUseCase.CreatePollResponse poll = createPollUseCase.createPoll(authenticationUser.getUser(), description, startProposalsTime, endProposalsTime, endVotingTime);
        return Views.VIEW_POLL;
    }
}
