package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.poll.CreatePollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.DeletePollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ListPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.LoadPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ModerationCloseUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ModerationOpenUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.PollProposalUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.PollUnvoteUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.PollVoteUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.service.StringEscapeService;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
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
  private final ModerationOpenUseCase moderationOpenUseCase;
  private final ModerationCloseUseCase moderationCloseUseCase;

  @Autowired
  public PollController(
      StringEscapeService stringEscapeService,
      CreatePollUseCase createPollUseCase,
      ListPollUseCase listPollUseCase,
      LoadPollUseCase loadPollUseCase,
      PollProposalUseCase pollProposalUseCase,
      PollVoteUseCase pollVoteUseCase,
      DeletePollUseCase deletePollUseCase,
      PollUnvoteUseCase pollUnvoteUseCase,
      ModerationOpenUseCase moderationOpenUseCase,
      ModerationCloseUseCase moderationCloseUseCase) {
    this.stringEscapeService = stringEscapeService;
    this.createPollUseCase = createPollUseCase;
    this.listPollUseCase = listPollUseCase;
    this.loadPollUseCase = loadPollUseCase;
    this.pollProposalUseCase = pollProposalUseCase;
    this.pollVoteUseCase = pollVoteUseCase;
    this.deletePollUseCase = deletePollUseCase;
    this.pollUnvoteUseCase = pollUnvoteUseCase;
    this.moderationOpenUseCase = moderationOpenUseCase;
    this.moderationCloseUseCase = moderationCloseUseCase;
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
      @RequestParam("endRankingTime")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime endRankingTime,
      @RequestParam("type")
          String type,
      @AuthenticationPrincipal
          AuthenticationUser authenticationUser
  ) {
    createPollUseCase.createPoll(
        authenticationUser.getUser(),
        stringEscapeService.escapeHTML(description),
        startProposalsTime,
        endProposalsTime,
        endVotingTime,
        startEventTime,
        endRankingTime,
        type);
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
    PollProposalUseCase.PollProposalResponse pollProposalResponse = pollProposalUseCase.pollProposal(
        pollId,
        authenticationUser.getUser(),
        StringUtils.trimToNull(hashtag),
        stringEscapeService.escapeHTML(StringUtils.trimToNull(subject)));
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
    PollVoteUseCase.PollVoteResponse pollVoteResponse =
        pollVoteUseCase.pollVote(pollId, proposalAuthorId, authenticationUser.getUser());

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

  @GetMapping("/poll/{pollId}/moderate/{proposalAuthorId}")
  @Secured("ROLE_ADMIN")
  public String openModeration(
      @PathVariable("pollId") String pollId,
      @PathVariable("proposalAuthorId") String proposalAuthorId,
      Model model
  ) {
    ModerationOpenUseCase.ModerationOpenUseCaseResponse moderationOpenUseCaseResponse =
        moderationOpenUseCase.loadProposal(pollId, proposalAuthorId);
    model.addAttribute("proposal", moderationOpenUseCaseResponse.getProposal());
    model.addAttribute("stringEscapeService", stringEscapeService);
    return "moderate";
  }

  @PostMapping("/poll/{pollId}/moderate/{proposalAuthorId}")
  @Secured("ROLE_ADMIN")
  public String closeModeration(
      @PathVariable("pollId")
          String pollId,
      @PathVariable("proposalAuthorId")
          String proposalAuthorId,
      @RequestParam("hashtag")
          String hashtag,
      @RequestParam("subject")
          String subject,
      @RequestParam(name = "cancelationReason", required = false)
          String cancelationReason,
      @AuthenticationPrincipal
          AuthenticationUser authenticationUser
  ) {
    moderationCloseUseCase.closeModeration(
        pollId,
        proposalAuthorId,
        StringUtils.trimToNull(hashtag),
        stringEscapeService.escapeHTML(StringUtils.trimToNull(subject)),
        StringUtils.isBlank(cancelationReason) ? null
            : stringEscapeService.escapeHTML(StringUtils.trimToNull(cancelationReason)),
        authenticationUser.getUser());
    return "redirect:/poll/" + pollId;
  }
}
