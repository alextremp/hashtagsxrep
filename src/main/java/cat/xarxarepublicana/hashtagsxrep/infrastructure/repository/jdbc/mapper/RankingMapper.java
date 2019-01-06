package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankingMapper {

    List<Score> selectTaggersRanking();
}
