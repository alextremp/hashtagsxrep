package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.util.List;

public interface PollRepository {

    void save(Poll poll);

    List<Poll> findLast();

    Poll findById(String id);

    Boolean hasProposal(String pollId, String authorId);

    void addProposal(Proposal proposal);

    Proposal findProposal(String pollId, String authorId);

    List<Proposal> findPollProposals(String pollId);

    void addVote(Proposal proposal, User voter);

    Proposal findUserVote(String pollId, String voterId);
}
