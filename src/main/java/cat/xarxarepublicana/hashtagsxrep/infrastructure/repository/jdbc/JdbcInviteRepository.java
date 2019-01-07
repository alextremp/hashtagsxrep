package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.invite.Invite;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteGroup;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.InviteMapper;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcInviteRepository implements InviteRepository {

    private final InviteMapper inviteMapper;

    public JdbcInviteRepository(InviteMapper inviteMapper) {
        this.inviteMapper = inviteMapper;
    }

    @Override
    public void inviteToPoll(String pollId) {
        inviteMapper.insertAdmins(pollId);
        inviteMapper.insertTaggers(pollId);
        inviteMapper.insertInfluencers(pollId);
        inviteMapper.insertTopRanking(pollId);
        inviteMapper.insertRandomRanking(pollId);
    }

    @Override
    public InviteGroup loadInvitesForPoll(String pollId) {
        List<Invite> inviteList = inviteMapper.selectInvitesByPollId(pollId);
        return new InviteGroup(
                inviteList.stream().filter(invite -> Invite.REASON_ADMIN.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_TAGGER.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_INFLUENCE.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_SCORE.equals(invite.getReason())).collect(Collectors.toList()),
                inviteList.stream().filter(invite -> Invite.REASON_RANDOM.equals(invite.getReason())).collect(Collectors.toList())
        );
    }
}
