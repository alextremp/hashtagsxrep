package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter;

import com.github.scribejava.core.builder.api.DefaultApi10a;

public class TwitterApi extends DefaultApi10a {
    private static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
    private static final String REQUEST_TOKEN_RESOURCE = "api.twitter.com/oauth/request_token";
    private static final String ACCESS_TOKEN_RESOURCE = "api.twitter.com/oauth/access_token";

    protected TwitterApi() {
    }

    public static TwitterApi instance() {
        return TwitterApi.InstanceHolder.INSTANCE;
    }

    public String getAccessTokenEndpoint() {
        return "https://api.twitter.com/oauth/access_token";
    }

    public String getRequestTokenEndpoint() {
        return "https://api.twitter.com/oauth/request_token";
    }

    public String getAuthorizationBaseUrl() {
        return "https://api.twitter.com/oauth/authorize";
    }

    public static class Authenticate extends TwitterApi {
        private static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate";

        private Authenticate() {
        }

        public static TwitterApi.Authenticate instance() {
            return TwitterApi.Authenticate.InstanceHolder.INSTANCE;
        }

        public String getAuthorizationBaseUrl() {
            return "https://api.twitter.com/oauth/authenticate";
        }

        private static class InstanceHolder {
            private static final TwitterApi.Authenticate INSTANCE = new TwitterApi.Authenticate();

            private InstanceHolder() {
            }
        }
    }

    private static class InstanceHolder {
        private static final TwitterApi INSTANCE = new TwitterApi();

        private InstanceHolder() {
        }
    }
}
