package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MonitorMapper {

    Monitor selectOneById(@Param("id") String id);

    void insert(@Param("monitor") Monitor monitor);

    List<Monitor> selectActive();

    void updateCursor(@Param("monitor") Monitor monitor);

    String selectMaxTweetId(@Param("monitorId") String monitorId);

    List<Monitor> selectLast(@Param("quantity") Integer qtt);

    Monitor selectOneByTwitterQuery(@Param("twitterQuery") String twitterQuery);

    void delete(@Param("monitorId") String monitorId);

    @Update("update MONITOR set active = FALSE where id = #{monitorId}")
    void disable(@Param("monitorId") String monitorId);
}
