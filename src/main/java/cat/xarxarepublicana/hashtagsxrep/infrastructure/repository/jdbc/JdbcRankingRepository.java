package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.RankingMapper;

public class JdbcRankingRepository implements RankingRepository {

    private final RankingMapper rankingMapper;

    public JdbcRankingRepository(RankingMapper rankingMapper) {
        this.rankingMapper = rankingMapper;
    }

    @Override
    public Ranking loadRanking() {
        return new Ranking(rankingMapper.selectTaggersRanking());
    }
}
