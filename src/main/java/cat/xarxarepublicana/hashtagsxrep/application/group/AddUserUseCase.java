package cat.xarxarepublicana.hashtagsxrep.application.group;

import cat.xarxarepublicana.hashtagsxrep.domain.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import cat.xarxarepublicana.hashtagsxrep.domain.group.GroupRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AddUserUseCase {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public AddUserUseCase(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public void addUser(String groupId, String nickname) {
        User user = userRepository.findByNickname(StringUtils.removeStart(nickname, "@"));
        if (user == null) {
            throw new EntityNotFoundException("L'usuari no es troba al nostre sistema: " + nickname + ", potser no ha participat mai.");
        }
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new EntityNotFoundException("El grup no existeix " + groupId);
        }
        groupRepository.addUser(group, user);
    }
}
