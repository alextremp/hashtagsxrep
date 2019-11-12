package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SearchTweetsResult {

  private Tweet[] statuses = new Tweet[0];
  private SearchMetadata searchMetadata = new SearchMetadata();

  public Tweet[] getStatuses() {
    return statuses;
  }

  public void setStatuses(Tweet[] statuses) {
    this.statuses = statuses;
  }

  public SearchMetadata getSearchMetadata() {
    return searchMetadata;
  }

  public void setSearchMetadata(SearchMetadata searchMetadata) {
    this.searchMetadata = searchMetadata;
  }
}
