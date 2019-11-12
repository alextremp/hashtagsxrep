package cat.xarxarepublicana.hashtagsxrep.domain.user;

import java.util.List;

public interface UserRepository {

  User findById(String id);

  User findByNickname(String nickname);

  void saveLoggedUser(User user);

  void saveExtractedUser(User user);

  List<User> findByGroupId(String groupId);
}
