package cat.xarxarepublicana.hashtagsxrep.application.group;

import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import cat.xarxarepublicana.hashtagsxrep.domain.group.GroupRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.JoiningUserNotice;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.NoticeRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class JoiningUserUseCase {

    private final GroupRepository groupRepository;
    private final NoticeRepository noticeRepository;

    public JoiningUserUseCase(GroupRepository groupRepository, NoticeRepository noticeRepository) {
        this.groupRepository = groupRepository;
        this.noticeRepository = noticeRepository;
    }

    public void addJoiningUser(User user) {
        Group group = groupRepository.findGroupToAddNewUser();
        Boolean mustCreateNewGroup = group != null;
        if (!mustCreateNewGroup) {
            groupRepository.addUser(group, user);
        }
        JoiningUserNotice notice = new JoiningUserNotice(user.getNickname(), group != null ? group.getId() : null);
        noticeRepository.publishJoiningUser(notice);
    }
}
