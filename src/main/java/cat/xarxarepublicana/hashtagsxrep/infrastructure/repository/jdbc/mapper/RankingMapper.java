package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.HashtagScore;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Score;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RankingMapper {

  List<Score> selectTaggersRanking();

  List<HashtagScore> selectUserScoreDetail(@Param("nickname") String nickname);
}
