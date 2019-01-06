package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.HashtagScore;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RankingMapper {

    List<Score> selectTaggersRanking();

    List<HashtagScore> selectUserScoreDetail(@Param("nickname") String nickname);
}
