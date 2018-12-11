package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.monitor.CreateMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.ListMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.ConnectTwitterCallbackUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.SignInWithTwitterUse;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationContext;
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
}
