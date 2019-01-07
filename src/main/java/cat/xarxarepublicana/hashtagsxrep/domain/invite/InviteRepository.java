package cat.xarxarepublicana.hashtagsxrep.domain.invite;

public interface InviteRepository {

    void inviteToPoll(String pollId);

    InviteGroup loadInvitesForPoll(String pollId);
}
