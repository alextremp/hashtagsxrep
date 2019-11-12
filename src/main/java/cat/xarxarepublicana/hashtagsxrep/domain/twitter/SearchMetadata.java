package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SearchMetadata {
  private String query;
  private String sinceIdStr;
  private String maxIdStr;
  private String nextResults;

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getSinceIdStr() {
    return sinceIdStr;
  }

  public void setSinceIdStr(String sinceIdStr) {
    this.sinceIdStr = sinceIdStr;
  }

  public String getMaxIdStr() {
    return maxIdStr;
  }

  public void setMaxIdStr(String maxIdStr) {
    this.maxIdStr = maxIdStr;
  }

  public String getNextResults() {
    return nextResults;
  }

  public void setNextResults(String nextResults) {
    this.nextResults = nextResults;
  }
}
