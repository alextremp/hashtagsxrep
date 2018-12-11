package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MonitorMapper {

    Monitor selectOneById(@Param("id") String id);

    void insert(@Param("monitor") Monitor monitor);

    List<Monitor> selectActive();
}
