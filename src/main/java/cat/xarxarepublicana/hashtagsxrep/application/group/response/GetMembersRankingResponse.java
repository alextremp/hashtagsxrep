package cat.xarxarepublicana.hashtagsxrep.application.group.response;

import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;

public class GetMembersRankingResponse {
    private final Group group;
    private final Ranking ranking;

    public GetMembersRankingResponse(Group group, Ranking ranking) {
        this.group = group;
        this.ranking = ranking;
    }

    public Group getGroup() {
        return group;
    }

    public Ranking getRanking() {
        return ranking;
    }
}
