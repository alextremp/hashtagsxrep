package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.poll.CreatePollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ListPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.LoadPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PollController {

    private final CreatePollUseCase createPollUseCase;
    private final ListPollUseCase listPollUseCase;
    private final LoadPollUseCase loadPollUseCase;

    @Autowired
    public PollController(CreatePollUseCase createPollUseCase, ListPollUseCase listPollUseCase, LoadPollUseCase loadPollUseCase) {
        this.createPollUseCase = createPollUseCase;
        this.listPollUseCase = listPollUseCase;
        this.loadPollUseCase = loadPollUseCase;
    }

    @GetMapping(Views.URL_POLL)
    @Secured("ROLE_TAGGER")
    public String listPolls(Model model) {
        ListPollUseCase.ListPollResponse listPollResponse = listPollUseCase.listPoll();
        model.addAttribute("pollList", listPollResponse.getPollList());
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
            @RequestParam("startEventTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime startEventTime,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser,
            Model model
    ) {
        CreatePollUseCase.CreatePollResponse poll = createPollUseCase.createPoll(authenticationUser.getUser(), description, startProposalsTime, endProposalsTime, endVotingTime, startEventTime);
        ListPollUseCase.ListPollResponse listPollResponse = listPollUseCase.listPoll();
        model.addAttribute("pollList", listPollResponse.getPollList());
        return Views.VIEW_POLL;
    }

    @GetMapping("/poll/{pollId}")
    @Secured("ROLE_TAGGER")
    public String poll(
            @PathVariable("pollId") String pollId,
            Model model
    ) {
        LoadPollUseCase.LoadPollResponse loadPollResponse = loadPollUseCase.loadPoll(pollId);
        model.addAttribute("poll", loadPollResponse.getPoll());
        return Views.VIEW_POLL_DETAIL;
    }

}
