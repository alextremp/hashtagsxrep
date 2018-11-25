package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.controller;

import cat.xarxarepublicana.hashtagsxrep.domain.User;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.InMemoryUserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.SecurityContext;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.UserAuth;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter.TwitterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PoCController {

  private static final Logger LOG = Logger.getLogger(PoCController.class.getName());

  private final TwitterClient twitterClient;
  private final InMemoryUserRepository userRepository;
  private final SecurityContext securityContext;

  @Autowired
  public PoCController(
      SecurityContext securityContext,
      InMemoryUserRepository userRepository,
      TwitterClient twitterClient
  ) {
    this.twitterClient = twitterClient;
    this.securityContext = securityContext;
    this.userRepository = userRepository;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/connect/twitter")
  public RedirectView connectTwitter(
          HttpServletResponse response,
          @RequestParam(value = "oauth_token", required = false) String oauthToken,
          @RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
          @RequestParam(value = "denied", required = false) String denied) throws Exception {

    if (denied != null) {
      LOG.info(">> /login/twitter: DENIED");
      return new RedirectView("/login");
    } else {
      LOG.info(">> /login/twitter: ACCEPTED: " + oauthToken);
      try {
        User user = twitterClient.getUser(oauthToken, oauthVerifier);
        UserAuth userAuth = new UserAuth(user);
        userRepository.save(userAuth);
        securityContext.put(userAuth, response);
      } catch(Exception e) {
        LOG.log(Level.SEVERE, "Error getting user", e);
        throw e;
      }
      return new RedirectView("/hello");
    }
  }

  @GetMapping("/hello")
  public String hello(Model model, @AuthenticationPrincipal UserAuth userAuth) {
    model.addAttribute("name", userAuth.getUser().getName());
    model.addAttribute("profileImage", userAuth.getUser().getProfileImageUrl());
    model.addAttribute("nickname", userAuth.getUser().getNickname());
    return "hello";
  }

  @GetMapping("/login/twitter")
  public RedirectView signInWithTwitter() throws Exception {
    String url = twitterClient.authTokenUrl();
    LOG.info(">> /login/twitter: " + url);
    return new RedirectView(url);
  }

  @GetMapping("/login")
  public String login() throws Exception {
    return "login";
  }
}
