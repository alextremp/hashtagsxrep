package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.UserRank;
import com.github.benmanes.caffeine.cache.LoadingCache;

import static cat.xarxarepublicana.hashtagsxrep.infrastructure.cache.CacheConstants.SINGLE_ENTRY_KEY;

public class CachedRankingRepository implements RankingRepository {

  private final RankingRepository rankingRepository;
  private final LoadingCache<String, Ranking> rankingCache;

  public CachedRankingRepository(RankingRepository rankingRepository, LoadingCache<String, Ranking> rankingCache) {
    this.rankingRepository = rankingRepository;
    this.rankingCache = rankingCache;
  }

  @Override
  public Ranking loadRanking() {
    return rankingCache.get(SINGLE_ENTRY_KEY);
  }

  @Override
  public UserRank loadUserRank(String nickname) {
    return rankingRepository.loadUserRank(nickname);
  }
}
