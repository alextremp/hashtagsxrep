package cat.xarxarepublicana.hashtagsxrep.domain.group;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import java.util.List;

public interface GroupRepository {
  List<Group> listAll();

  Group findById(String groupId);

  void addUser(Group group, User user);

  void removeUser(Group group, User user);
}
