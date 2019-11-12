package cat.xarxarepublicana.hashtagsxrep.application.group;

import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import cat.xarxarepublicana.hashtagsxrep.domain.group.GroupRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class RemoveUserUseCase {

  private final GroupRepository groupRepository;
  private final UserRepository userRepository;

  public RemoveUserUseCase(GroupRepository groupRepository, UserRepository userRepository) {
    this.groupRepository = groupRepository;
    this.userRepository = userRepository;
  }

  public void remove(String groupId, String nickname) {
    Group group = groupRepository.findById(groupId);
    User user = userRepository.findByNickname(StringUtils.removeStart(nickname, "@"));
    if (group != null && user != null) {
      groupRepository.removeUser(group, user);
    }
  }
}
