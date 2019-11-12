package cat.xarxarepublicana.hashtagsxrep.domain.ranking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRank {

  private final Integer rank;
  private List<HashtagScore> hashtagScoreList;

  public UserRank(Integer rank, List<HashtagScore> hashtagScoreList) {
    this.rank = rank;
    this.hashtagScoreList = hashtagScoreList != null ? hashtagScoreList : new ArrayList<>();
  }

  public Integer getRank() {
    return rank;
  }

  public List<HashtagScore> getHashtagScoreList() {
    return hashtagScoreList;
  }

  public Integer getTotalScore() {
    return getHashtagScoreList()
        .stream()
        .map(hashtagScore -> hashtagScore.getScore())
        .collect(Collectors.summingInt(x -> x));
  }
}
