package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.monitor.CreateMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.ListMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.MonitorDataExtractionUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.poll.*;
import cat.xarxarepublicana.hashtagsxrep.application.report.GetTwitterQueryReportUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.ConnectTwitterCallbackUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.SignInWithTwitterUse;
import cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtractionRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.ProposalFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.report.ReportRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public ConnectTwitterCallbackUseCase connectTwitterCallbackUseCase(UserRepository userRepository, TwitterRepository twitterRepository, AuthenticationContext authenticationContext) {
        return new ConnectTwitterCallbackUseCase(userRepository, twitterRepository, authenticationContext);
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
    public ListMonitorUseCase listMonitorUseCase(MonitorRepository monitorRepository) {
        return new ListMonitorUseCase(monitorRepository);
    }

    @Bean
    public MonitorDataExtractionUseCase monitorDataExtractionUseCase(@Value("${app.monitor.extraction.maxRequests}") Integer maxExtractionRequests, TwitterExtractionRepository twitterExtractionRepository, TwitterRepository twitterRepository, MonitorRepository monitorRepository) {
        return new MonitorDataExtractionUseCase(maxExtractionRequests, twitterExtractionRepository, twitterRepository, monitorRepository);
    }

    @Bean
    public GetTwitterQueryReportUseCase getTwitterQueryReportUseCase(ReportRepository reportRepository, MonitorRepository monitorRepository) {
        return new GetTwitterQueryReportUseCase(reportRepository, monitorRepository);
    }

    @Bean
    public CreatePollUseCase createPollUseCase(PollFactory pollFactory, PollRepository pollRepository) {
        return new CreatePollUseCase(pollFactory, pollRepository);
    }

    @Bean
    public ListPollUseCase listPollUseCase(PollRepository pollRepository) {
        return new ListPollUseCase(pollRepository);
    }

    @Bean
    public LoadPollUseCase loadPollUseCase(PollRepository pollRepository) {
        return new LoadPollUseCase(pollRepository);
    }

    @Bean
    public PollProposalUseCase pollProposalUseCase(PollRepository pollRepository, ProposalFactory proposalFactory) {
        return new PollProposalUseCase(pollRepository, proposalFactory);
    }

    @Bean
    public PollVoteUseCase pollVoteUseCase(PollRepository pollRepository) {
        return new PollVoteUseCase(pollRepository);
    }

    @Bean
    public CreateMonitorFromPollUseCase createMonitorFromPollUseCase(MonitorRepository monitorRepository, MonitorFactory monitorFactory, PollRepository pollRepository) {
        return new CreateMonitorFromPollUseCase(monitorRepository, monitorFactory, pollRepository);
    }

    @Bean
    public DeletePollUseCase deletePollUseCase(PollRepository pollRepository) {
        return new DeletePollUseCase(pollRepository);
    }
}
