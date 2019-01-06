package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.HashtagScore;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.UserRank;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.RankingMapper;

import java.util.List;

public class JdbcRankingRepository implements RankingRepository {

    private final RankingMapper rankingMapper;

    public JdbcRankingRepository(RankingMapper rankingMapper) {
        this.rankingMapper = rankingMapper;
    }

    @Override
    public Ranking loadRanking() {
        return new Ranking(rankingMapper.selectTaggersRanking());
    }

    @Override
    public UserRank loadUserRank(String nickname) {
        List<HashtagScore> hashtagScoreList = rankingMapper.selectUserScoreDetail(nickname);
        Integer rank = loadRanking().getRankByNickname(nickname);
        return new UserRank(rank, hashtagScoreList);
    }
}
