package twitterclient;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class RunGetTweetsMain extends TwitterClientMain {

    private static final Logger LOG = Logger.getLogger(RunGetTweetsMain.class.getName());

    public static void main(String[] args) throws Exception {
        LOG.info(">> init");
        RunGetTweetsMain main = new RunGetTweetsMain();
        main.run(false);
        main.run(true);
    }

    public void run(boolean map) throws InterruptedException, ExecutionException, IOException {
   /*     LOG.info("env >> " + env.getProperty("OAUTH_TWITTER_CONSUMER_API_KEY"));

        String SEARCH_TWEETS = "https://api.twitter.com/1.1/search/tweets.json";


        ObjectMapper objectMapper = new ObjectMapper();
        OAuthRequest request;
        Response response;
        SearchTweetsResult searchTweetsResult;
        String sinceId = "0";

        boolean stop = false;
        while(!stop) {
            request = new OAuthRequest(Verb.GET, SEARCH_TWEETS);
            *//*
             * - not found twett does not return next search
             * - but it returns a max_id that should be the next since_id to search from
             * *//*
            request.addQuerystringParameter("q", "divendresxrep");
            request.addQuerystringParameter("since", "2018-10-27");
            request.addQuerystringParameter("count", "100");
            request.addQuerystringParameter("result_type", "mixed");
            request.addQuerystringParameter("include_entities", "false");
            request.addQuerystringParameter("since_id", sinceId);
            oAuth10aService.signRequest(appAccessToken, request);

            response = oAuth10aService.execute(request);
            if (!map) {
                LOG.info("response >> " + response.getBody());
                return;
            }

            searchTweetsResult = objectMapper.readValue(response.getStream(), SearchTweetsResult.class);
            LOG.info(">> SINCE[" + sinceId + "] result >> " + objectMapper.writeValueAsString(searchTweetsResult));

            stop = searchTweetsResult.getSearchMetadata().getNextResults() == null;
            if (!stop) {
                sinceId = searchTweetsResult.getSearchMetadata().getMaxIdStr();
            }
        }*/
    }
}
