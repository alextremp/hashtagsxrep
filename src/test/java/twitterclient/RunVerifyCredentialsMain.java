package twitterclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class RunVerifyCredentialsMain extends TwitterClientMain {

    private static final Logger LOG = Logger.getLogger(RunVerifyCredentialsMain.class.getName());

    public static void main(String[] args) {
        LOG.info(">> init");
        RunVerifyCredentialsMain main = new RunVerifyCredentialsMain();
        main.run();
    }

    private void run() {
        this.twitterRepository.verifyCredentials(appToken, appSecret);
    }
}
