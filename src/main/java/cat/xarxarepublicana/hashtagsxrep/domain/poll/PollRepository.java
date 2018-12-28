package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.util.List;

public interface PollRepository {

    void save(Poll poll);

    List<Poll> findActive();

    Poll findById(String id);

    Boolean hasProposal(String pollId, String authorId);

    void addProposal(Proposal proposal);

    Proposal findProposal(String pollId, String authorId);

    List<Proposal> findPollProposals(String pollId);
}
