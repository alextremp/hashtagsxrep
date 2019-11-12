package cat.xarxarepublicana.hashtagsxrep.application.group;

import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import cat.xarxarepublicana.hashtagsxrep.domain.group.GroupRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ListGroupsUseCase {

  private final GroupRepository groupRepository;

  public ListGroupsUseCase(GroupRepository groupRepository) {
    this.groupRepository = groupRepository;
  }

  public List<Group> listGroups() {
    return groupRepository.listAll();
  }
}
