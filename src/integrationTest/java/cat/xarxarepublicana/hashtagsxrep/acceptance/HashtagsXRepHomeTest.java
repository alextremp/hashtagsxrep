package cat.xarxarepublicana.hashtagsxrep.acceptance;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class HashtagsXRepHomeTest extends AbstractIntegrationTest {

  @Test
  public void shouldGoToIndex() {
    HtmlPage page = page("/");
    assertEquals("Inici | Hashtags per la República", page.getTitleText());
    final String pageAsText = page.asText();
    System.out.println(pageAsText);
  }

  @Test
  public void shouldGoToReport() {
    HtmlPage page = page("/report/TestHashtagsxrep2");
    final String pageAsText = page.asText();
    System.out.println(pageAsText);
    assertEquals("Resultats | Hashtags per la República", page.getTitleText());
  }
}
