package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.poll.*;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.service.StringEscapeService;
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
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
public class PollController {

    private final StringEscapeService stringEscapeService;
    private final CreatePollUseCase createPollUseCase;
    private final ListPollUseCase listPollUseCase;
    private final LoadPollUseCase loadPollUseCase;
    private final PollProposalUseCase pollProposalUseCase;
    private final PollVoteUseCase pollVoteUseCase;
    private final DeletePollUseCase deletePollUseCase;
    private final PollUnvoteUseCase pollUnvoteUseCase;

    @Autowired
    public PollController(StringEscapeService stringEscapeService, CreatePollUseCase createPollUseCase, ListPollUseCase listPollUseCase, LoadPollUseCase loadPollUseCase, PollProposalUseCase pollProposalUseCase, PollVoteUseCase pollVoteUseCase, DeletePollUseCase deletePollUseCase, PollUnvoteUseCase pollUnvoteUseCase) {
        this.stringEscapeService = stringEscapeService;
        this.createPollUseCase = createPollUseCase;
        this.listPollUseCase = listPollUseCase;
        this.loadPollUseCase = loadPollUseCase;
        this.pollProposalUseCase = pollProposalUseCase;
        this.pollVoteUseCase = pollVoteUseCase;
        this.deletePollUseCase = deletePollUseCase;
        this.pollUnvoteUseCase = pollUnvoteUseCase;
    }

    @GetMapping(Views.URL_POLL)
    public String listPolls(Model model) {
        ListPollUseCase.ListPollResponse listPollResponse = listPollUseCase.listPoll();
        model.addAttribute("pollList", listPollResponse.getPollList());
        return Views.VIEW_POLL;
    }

    @PostMapping(Views.URL_POLL)
    @Secured("ROLE_ADMIN")
    public RedirectView createPoll(
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
                    AuthenticationUser authenticationUser
    ) {
        CreatePollUseCase.CreatePollResponse createPollResponse = createPollUseCase.createPoll(authenticationUser.getUser(), stringEscapeService.escapeHTML(description), startProposalsTime, endProposalsTime, endVotingTime, startEventTime);
        //TODO check to pass the model
        return new RedirectView(Views.URL_POLL);
    }

    @GetMapping("/poll/{pollId}")
    public String poll(
            @PathVariable("pollId") String pollId,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser,
            Model model
    ) {
        LoadPollUseCase.LoadPollResponse loadPollResponse = loadPollUseCase.loadPoll(pollId, authenticationUser.getUser());
        model.addAttribute("loadPollResponse", loadPollResponse);
        model.addAttribute("user", authenticationUser.getUser());
        model.addAttribute("stringEscapeService", stringEscapeService);
        return Views.VIEW_POLL_DETAIL;
    }

    @PostMapping("/poll/{pollId}/proposal")
    @Secured("ROLE_VIEWER")
    public RedirectView pollProposal(
            @PathVariable("pollId") String pollId,
            @RequestParam("hashtag")
                    String hashtag,
            @RequestParam("subject")
                    String subject,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser,
            Model model
    ) {
        PollProposalUseCase.PollProposalResponse pollProposalResponse = pollProposalUseCase.pollProposal(pollId, authenticationUser.getUser(), hashtag, stringEscapeService.escapeHTML(subject));
        model.addAttribute("pollProposalResponse", pollProposalResponse);
        return new RedirectView("/poll/" + pollId);
    }

    @PostMapping("/poll/{pollId}/vote")
    @Secured("ROLE_VIEWER")
    public RedirectView pollVote(
            @PathVariable("pollId") String pollId,
            @RequestParam("proposalAuthorId")
                    String proposalAuthorId,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser,
            Model model
    ) {
        PollVoteUseCase.PollVoteResponse pollVoteResponse = pollVoteUseCase.pollVote(pollId, proposalAuthorId, authenticationUser.getUser());

        model.addAttribute("pollVoteResponse", pollVoteResponse);
        return new RedirectView("/poll/" + pollId);
    }

    @PostMapping("/poll/{pollId}/unvote")
    @Secured("ROLE_VIEWER")
    public RedirectView pollVote(
            @PathVariable("pollId") String pollId,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser
    ) {
        pollUnvoteUseCase.pollUnvote(pollId, authenticationUser.getUser());
        return new RedirectView("/poll/" + pollId);
    }

    @PostMapping("/poll/{pollId}/delete")
    @Secured("ROLE_ADMIN")
    public RedirectView pollDelete(
            @PathVariable("pollId") String pollId,
            @RequestParam("description") String description
    ) {
        deletePollUseCase.deletePoll(pollId, stringEscapeService.escapeHTML(description));
        return new RedirectView(Views.URL_POLL);
    }

}
