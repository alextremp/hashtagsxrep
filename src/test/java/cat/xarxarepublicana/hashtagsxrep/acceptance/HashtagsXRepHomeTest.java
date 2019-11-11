package cat.xarxarepublicana.hashtagsxrep.acceptance;

import cat.xarxarepublicana.hashtagsxrep.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

public class HashtagsXRepHomeTest extends AbstractIntegrationTest {

  @Test
  public void shouldGoToIndex() {
    validatableGet("/")
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .body("status", equalTo("UP"));
  }
}
