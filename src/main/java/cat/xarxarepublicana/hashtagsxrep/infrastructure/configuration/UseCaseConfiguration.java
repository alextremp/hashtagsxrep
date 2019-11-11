package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.monitor.CreateMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.DeleteMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.ListMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.MonitorDataExtractionUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.CreatePollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.DeletePollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.FinishPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ListPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.LoadPollUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ModerationCloseUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ModerationOpenUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.PollProposalUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.PollUnvoteUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.PollVoteUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.ValidateHashtagUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.ranking.GetTaggersRankingUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.report.GetTwitterQueryReportUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.ConnectTwitterCallbackUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.SignInWithTwitterUse;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.NoticeRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.ProposalFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.report.ReportRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterSearchService;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthCookieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

  @Bean
  public ConnectTwitterCallbackUseCase connectTwitterCallbackUseCase(
      UserRepository userRepository,
      TwitterRepository twitterRepository,
      AuthCookieService authCookieService) {
    return new ConnectTwitterCallbackUseCase(userRepository, twitterRepository, authCookieService);
  }

  @Bean
  public SignInWithTwitterUse signInWithTwitterUse(TwitterRepository twitterRepository) {
    return new SignInWithTwitterUse(twitterRepository);
  }

  @Bean
  public CreateMonitorUseCase createMonitorUseCase(MonitorRepository monitorRepository, MonitorFactory monitorFactory) {
    return new CreateMonitorUseCase(monitorRepository, monitorFactory);
  }

  @Bean
  public DeleteMonitorUseCase deleteMonitorUseCase(
      MonitorRepository monitorRepository,
      TwitterExtractionRepository twitterExtractionRepository) {
    return new DeleteMonitorUseCase(monitorRepository, twitterExtractionRepository);
  }

  @Bean
  public ListMonitorUseCase listMonitorUseCase(MonitorRepository monitorRepository) {
    return new ListMonitorUseCase(monitorRepository);
  }

  @Bean
  public MonitorDataExtractionUseCase monitorDataExtractionUseCase(
      @Value("${app.monitor.extraction.maxRequests}") Integer maxExtractionRequests,
      TwitterExtractionRepository twitterExtractionRepository,
      TwitterRepository twitterRepository,
      MonitorRepository monitorRepository,
      PollRepository pollRepository) {
    return new MonitorDataExtractionUseCase(
        maxExtractionRequests,
        twitterExtractionRepository,
        twitterRepository,
        monitorRepository,
        pollRepository);
  }

  @Bean
  public GetTwitterQueryReportUseCase getTwitterQueryReportUseCase(
      ReportRepository reportRepository,
      MonitorRepository monitorRepository) {
    return new GetTwitterQueryReportUseCase(reportRepository, monitorRepository);
  }

  @Bean
  public CreatePollUseCase createPollUseCase(
      PollFactory pollFactory,
      PollRepository pollRepository,
      InviteRepository inviteRepository) {
    return new CreatePollUseCase(pollFactory, pollRepository, inviteRepository);
  }

  @Bean
  public ListPollUseCase listPollUseCase(PollRepository pollRepository) {
    return new ListPollUseCase(pollRepository);
  }

  @Bean
  public LoadPollUseCase loadPollUseCase(
      PollRepository pollRepository,
      InviteRepository inviteRepository,
      MonitorRepository monitorRepository) {
    return new LoadPollUseCase(pollRepository, inviteRepository, monitorRepository);
  }

  @Bean
  public PollProposalUseCase pollProposalUseCase(
      PollRepository pollRepository,
      ProposalFactory proposalFactory,
      InviteRepository inviteRepository,
      NoticeRepository noticeRepository) {
    return new PollProposalUseCase(pollRepository, proposalFactory, inviteRepository, noticeRepository);
  }

  @Bean
  public PollVoteUseCase pollVoteUseCase(PollRepository pollRepository) {
    return new PollVoteUseCase(pollRepository);
  }

  @Bean
  public PollUnvoteUseCase pollUnvoteUseCase(PollRepository pollRepository) {
    return new PollUnvoteUseCase(pollRepository);
  }

  @Bean
  public FinishPollUseCase createFinishPollUseCase(
      MonitorRepository monitorRepository,
      MonitorFactory monitorFactory,
      PollRepository pollRepository,
      NoticeRepository noticeRepository) {
    return new FinishPollUseCase(monitorRepository, monitorFactory, pollRepository, noticeRepository);
  }

  @Bean
  public DeletePollUseCase deletePollUseCase(PollRepository pollRepository) {
    return new DeletePollUseCase(pollRepository);
  }

  @Bean
  public GetTaggersRankingUseCase getTaggersRankingUseCase(RankingRepository rankingRepository) {
    return new GetTaggersRankingUseCase(rankingRepository);
  }

  @Bean
  public ValidateHashtagUseCase validateHashtagUseCase(
      TwitterSearchService twitterSearchService,
      MonitorRepository monitorRepository) {
    return new ValidateHashtagUseCase(twitterSearchService, monitorRepository);
  }

  @Bean
  public ModerationOpenUseCase moderationOpenUseCase(PollRepository pollRepository) {
    return new ModerationOpenUseCase(pollRepository);
  }

  @Bean
  public ModerationCloseUseCase moderationCloseUseCase(PollRepository pollRepository) {
    return new ModerationCloseUseCase(pollRepository);
  }
}
