package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteGroup;
import cat.xarxarepublicana.hashtagsxrep.domain.invite.InviteRepository;
import com.github.benmanes.caffeine.cache.LoadingCache;

public class CachedInviteRepository implements InviteRepository {

  private final InviteRepository inviteRepository;
  private final LoadingCache<String, InviteGroup> inviteGroupCache;

  public CachedInviteRepository(InviteRepository inviteRepository, LoadingCache<String, InviteGroup> inviteGroupCache) {
    this.inviteRepository = inviteRepository;
    this.inviteGroupCache = inviteGroupCache;
  }

  @Override
  public void inviteToPoll(String pollId, String type) {
    inviteRepository.inviteToPoll(pollId, type);
    inviteGroupCache.invalidate(pollId);
  }

  @Override
  public InviteGroup loadInvitesForPoll(String pollId) {
    return inviteGroupCache.get(pollId);
  }
}
