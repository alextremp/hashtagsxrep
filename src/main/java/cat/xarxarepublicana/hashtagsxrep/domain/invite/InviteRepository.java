package cat.xarxarepublicana.hashtagsxrep.domain.invite;

public interface InviteRepository {

    void inviteToPoll(String pollId, String type);

    InviteGroup loadInvitesForPoll(String pollId);
}
