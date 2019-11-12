package cat.xarxarepublicana.hashtagsxrep.domain.ranking;

public interface RankingRepository {

  Ranking loadRanking();

  UserRank loadUserRank(String nickname);
}
