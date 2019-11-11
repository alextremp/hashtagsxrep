package cat.xarxarepublicana.hashtagsxrep.acceptance;

import cat.xarxarepublicana.hashtagsxrep.HashtagsXRepTestApplication;
import cat.xarxarepublicana.hashtagsxrep.helper.InitDbHelper;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthCookieService;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthToken;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.net.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MariaDBContainer;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integration-test")
@SpringBootTest(
    classes = {HashtagsXRepTestApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {AbstractIntegrationTest.Initializer.class})
public abstract class AbstractIntegrationTest {

  private static final MariaDBContainer DB_CONTAINER =
      (MariaDBContainer) new MariaDBContainer("mariadb:10.3.6")
          .withDatabaseName("hashtagsxrep")
          .withUsername("htags")
          .withPassword("htags")
          .withReuse(true);

  static {
    /*
      Sharing same container between multiple Test classes does not work properly,
      instead of using @TestContainers and @Container annotations, the only way to make it work
      is to start a shared container statically on initialization.
      @see https://github.com/testcontainers/testcontainers-java/issues/417
     */
    DB_CONTAINER.start();
  }

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "app.db.url=" + DB_CONTAINER.getJdbcUrl(),
          "app.db.user=" + DB_CONTAINER.getUsername(),
          "app.db.password=" + DB_CONTAINER.getPassword())
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  private static final String TEST_AUTH_TOKEN =
      "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4dHJlbXAiLCJ1c2VySWQiOiI0MTk1OTU3OTM1IiwidXNlclRva2VuIjoiNDE5NTk1NzkzNS14eHh4eHhfaW52ZW50X3Rva2VuIn0.Mfh_4HrDTnTqC_SmsqEegewfNlUcRWctD7GuwfOufraH6msWDL23j9Tw0LTgmt76RRYTuwgT7uHIAkUTjeRBVQ";

  private String address;
  private WebClient webClient;

  @Autowired
  private TestRestTemplate restTemplate;
  @Value("${local.server.port}")
  private Integer port;
  @Autowired
  private InitDbHelper initDbHelper;
  @Autowired
  private AuthCookieService authCookieService;

  @Value("${app.usertoken.cookiename}")
  private String cookieName;

  @BeforeEach
  public void setUp() {
    address = "http://localhost:" + port;
    webClient = new WebClient();
    initDbHelper.initDb();
  }

  @AfterEach
  public void tearDown() {
    if (webClient != null) {
      webClient.close();
    }
  }

  protected HtmlPage page(String path) {
    return page(path, true);
  }

  protected HtmlPage page(String path, boolean authenticated) {
    try {
      WebRequest request = new WebRequest(new URL(address + path));
      request.setAdditionalHeader("Cookie", cookieName + "=" + testToken());
      return webClient.getPage(request);
    } catch (Exception e) {
      throw new RuntimeException("Error loading: " + path, e);
    }
  }

  private String testToken() {
    return authCookieService.serialize(new AuthToken("4195957935", "alextremp", "4195957935-xxxxxx_invent_token"));
  }
}
