package cat.xarxarepublicana.hashtagsxrep.application.ranking;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.UserRank;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

public class GetTaggersRankingUseCase {

  private final RankingRepository rankingRepository;

  public GetTaggersRankingUseCase(RankingRepository rankingRepository) {
    this.rankingRepository = rankingRepository;
  }

  public GetTaggersRankingResponse getTaggersRanking(User user) {
    Ranking ranking = rankingRepository.loadRanking();
    UserRank userRank = rankingRepository.loadUserRank(user.getNickname());
    return new GetTaggersRankingResponse(ranking, userRank);
  }

  public static class GetTaggersRankingResponse {

    private final Ranking ranking;
    private final UserRank userRank;

    public GetTaggersRankingResponse(Ranking ranking, UserRank userRank) {
      this.ranking = ranking;
      this.userRank = userRank;
    }

    public Ranking getRanking() {
      return ranking;
    }

    public UserRank getUserRank() {
      return userRank;
    }
  }
}
