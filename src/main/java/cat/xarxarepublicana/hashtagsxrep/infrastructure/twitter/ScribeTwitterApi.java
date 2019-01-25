package cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter;

import com.github.scribejava.core.builder.api.DefaultApi10a;

public class ScribeTwitterApi extends DefaultApi10a {
    private static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
    private static final String REQUEST_TOKEN_RESOURCE = "api.twitter.com/oauth/request_token";
    private static final String ACCESS_TOKEN_RESOURCE = "api.twitter.com/oauth/access_token";

    protected ScribeTwitterApi() {
    }

    public static ScribeTwitterApi instance() {
        return ScribeTwitterApi.InstanceHolder.INSTANCE;
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

    public static class Authenticate extends ScribeTwitterApi {
        private static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate";

        private Authenticate() {
        }

        public static ScribeTwitterApi.Authenticate instance() {
            return ScribeTwitterApi.Authenticate.InstanceHolder.INSTANCE;
        }

        public String getAuthorizationBaseUrl() {
            return "https://api.twitter.com/oauth/authenticate";
        }

        private static class InstanceHolder {
            private static final ScribeTwitterApi.Authenticate INSTANCE = new ScribeTwitterApi.Authenticate();

            private InstanceHolder() {
            }
        }
    }

    private static class InstanceHolder {
        private static final ScribeTwitterApi INSTANCE = new ScribeTwitterApi();

        private InstanceHolder() {
        }
    }
}