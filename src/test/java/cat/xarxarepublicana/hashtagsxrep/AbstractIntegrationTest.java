package cat.xarxarepublicana.hashtagsxrep;

import cat.xarxarepublicana.hashtagsxrep.configuration.IntegrationTestConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MariaDBContainer;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integration-test")
@SpringBootTest(
    classes = {HashtagsXRepTestApplication.class, IntegrationTestConfiguration.class},
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

  private String address;

  @Autowired
  private TestRestTemplate restTemplate;
  @Value("${local.server.port}")
  private Integer port;

  @BeforeEach
  public void setUp() {
    address = "http://localhost:" + port;
  }

  protected Response get(String relativePath) {
    return get(relativePath, HttpStatus.OK);
  }

  protected <T> T get(String relativePath, Class<T> expectedResponseClass) {
    return extract(get(relativePath, HttpStatus.OK), expectedResponseClass);
  }

  protected Response get(String relativePath, HttpStatus expectedStatus) {
    return get(relativePath, equalTo(expectedStatus.value()));
  }

  protected Response get(String relativePath, Matcher<Integer> statusMatcher) {
    return validatableGet(relativePath)
        .statusCode(statusMatcher)
        .extract()
        .response();
  }

  protected ValidatableResponse validatableGet(String relativePath) {
    return RestAssured.get(address + relativePath)
        .then()
        .contentType(ContentType.JSON);
  }

  protected Response post(String relativePath, String body) {
    return RestAssured
        .given()
        .body(body)
        .post(address + relativePath)
        .then()
        .extract()
        .response();
  }

  private <T> T extract(Response response, Class<T> expectedResponseClass) {
    T expectedResponse = response.as(expectedResponseClass);
    if (expectedResponse == null) {
      String stringResponse = response.asString();
      if (stringResponse != null) {
        throw new RuntimeException("Expecting: "
                                       + expectedResponseClass.getName()
                                       + " but received unknown response (status code: " + response.statusCode() + "): "
                                       + stringResponse);
      } else {
        throw new RuntimeException("Expecting: " + expectedResponseClass.getName() + " but received null");
      }
    }
    return expectedResponse;
  }
}
