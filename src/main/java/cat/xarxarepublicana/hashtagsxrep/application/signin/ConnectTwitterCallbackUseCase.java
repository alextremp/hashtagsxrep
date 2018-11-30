package cat.xarxarepublicana.hashtagsxrep.application.signin;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.AuthenticationContext;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.AuthenticationUser;

import javax.servlet.http.HttpServletResponse;

public class ConnectTwitterCallbackUseCase {

    private final UserRepository userRepository;
    private final TwitterRepository twitterRepository;
    private final AuthenticationContext authenticationContext;

    public ConnectTwitterCallbackUseCase(UserRepository userRepository, TwitterRepository twitterRepository, AuthenticationContext authenticationContext) {
        this.userRepository = userRepository;
        this.twitterRepository = twitterRepository;
        this.authenticationContext = authenticationContext;
    }

    public ConnectResponse connect(HttpServletResponse response, String oauthToken, String oauthVerifier, String denied) {
        if (denied != null) {
            return new ConnectResponse(Views.LOGIN);
        }
        TwitterUser twitterUser = twitterRepository.verifyCredentials(oauthToken, oauthVerifier);
        User user = userRepository.save(twitterUser, oauthToken, oauthVerifier);
        AuthenticationUser authenticationUser = new AuthenticationUser(user);
        authenticationContext.put(authenticationUser, response);
        return new ConnectResponse(Views.INDEX);
    }

    public static class ConnectResponse {
        private final String redirectTo;

        public ConnectResponse(String redirectTo) {
            this.redirectTo = redirectTo;
        }

        public String getRedirectTo() {
            return redirectTo;
        }
    }
}
