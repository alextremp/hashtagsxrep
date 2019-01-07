package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.InviteMapper;

public class JdbcInviteRepository implements InviteRepository {

    private final InviteMapper inviteMapper;

    public JdbcInviteRepository(InviteMapper inviteMapper) {
        this.inviteMapper = inviteMapper;
    }

    @Override
    public void inviteToPoll(Poll poll) {
        inviteMapper.insertAdmins(poll.getId());
        inviteMapper.insertTaggers(poll.getId());
        inviteMapper.insertInfluencers(poll.getId());
        inviteMapper.insertTopRanking(poll.getId());
        inviteMapper.insertRandomRanking(poll.getId());
    }
}
