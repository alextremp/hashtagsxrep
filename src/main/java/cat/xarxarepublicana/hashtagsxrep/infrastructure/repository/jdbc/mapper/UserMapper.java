package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  User selectOneById(@Param("id") String id);

  User selectOneByNickname(@Param("nickname") String nickname);

  List<User> selectByMembership(@Param("groupId") String groupId);

  void insert(@Param("user") User user);

  boolean exists(@Param("id") String id);

  void updateCredentialsData(@Param("user") User user);

  void updateTwitterData(@Param("user") User user);
}
