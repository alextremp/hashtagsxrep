package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.invite.Invite;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteGroup;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.InviteMapper;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public InviteGroup loadInvitesForPoll(Poll poll) {
        List<Invite> inviteList = inviteMapper.selectInvitesByPollId(poll.getId());
        return new InviteGroup(
                inviteList.stream().filter(invite -> Invite.REASON_ADMIN.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_TAGGER.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_INFLUENCE.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_SCORE.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_RANDOM.equals(invite.getReason())).collect(Collectors.toList())
        );
    }
}
