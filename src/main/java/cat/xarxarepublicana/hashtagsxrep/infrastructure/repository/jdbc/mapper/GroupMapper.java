package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupMapper {

    @ResultMap("GroupResultMap")
    @Select("select id from USER_GROUP order by id asc")
    List<Group> selectAll();

    @ResultMap("GroupResultMap")
    @Select("select id from USER_GROUP where id=#{groupId}")
    Group selectOneById(@Param("groupId") String groupId);

    @Insert("insert into USER_MEMBERSHIP values(#{groupId}, #{userId})")
    void insert(@Param("groupId") String groupId, @Param("userId") String userId);

    @Select("select exists (select 1 from USER_MEMBERSHIP where group_id = #{groupId} and user_id = #{userId})")
    boolean existsMembership(@Param("groupId") String groupId, @Param("userId") String userId);

    @Delete("delete from USER_MEMBERSHIP where group_id = #{groupId} and user_id = #{userId}")
    void delete(@Param("groupId") String groupId, @Param("userId") String userId);

    Group selectGroupToAddNextUser();
}
