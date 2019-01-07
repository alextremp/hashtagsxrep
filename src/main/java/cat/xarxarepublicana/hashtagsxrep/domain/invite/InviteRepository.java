package cat.xarxarepublicana.hashtagsxrep.domain.invite;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;

public interface InviteRepository {

    void inviteToPoll(Poll poll);
}
