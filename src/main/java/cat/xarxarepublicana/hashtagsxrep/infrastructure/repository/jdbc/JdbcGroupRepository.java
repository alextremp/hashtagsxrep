package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import cat.xarxarepublicana.hashtagsxrep.domain.group.GroupRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.GroupMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGroupRepository implements GroupRepository {

    private final GroupMapper groupMapper;

    public JdbcGroupRepository(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public List<Group> listAll() {
        return groupMapper.selectAll();
    }

    @Override
    public Group findById(String groupId) {
        return groupMapper.selectOneById(groupId);
    }

    @Override
    public void addUser(Group group, User user) {
        if (!groupMapper.existsMembership(group.getId(), user.getId())) {
            groupMapper.insert(group.getId(), user.getId());
        }
    }

    @Override
    public void removeUser(Group group, User user) {
        groupMapper.delete(group.getId(), user.getId());
    }

    @Override
    public Group findGroupToAddNewUser() {
        return groupMapper.selectGroupToAddNextUser();
    }
}
