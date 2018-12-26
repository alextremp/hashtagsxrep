package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.report.InteractorResume;
import cat.xarxarepublicana.hashtagsxrep.domain.report.TweetedContentResume;
import cat.xarxarepublicana.hashtagsxrep.domain.report.UserContentResume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReportMapper {

    InteractorResume selectInteractorResume(@Param("monitorId") String monitorId);

    TweetedContentResume selectTweetedContentResume(@Param("monitorId") String monitorId);

    Integer selectTaggersCount(@Param("monitorId") String monitorId);

    UserContentResume selectUserContentResume(@Param("monitorId") String monitorId);

    Integer selectMaxImpressions(@Param("monitorId") String monitorId);

    Integer selectMaxAudience(@Param("monitorId") String monitorId);
}
