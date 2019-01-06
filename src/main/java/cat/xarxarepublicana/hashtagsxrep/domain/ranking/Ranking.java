package cat.xarxarepublicana.hashtagsxrep.domain.ranking;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private final List<Score> taggerScoreList;

    public Ranking(List<Score> taggerScoreList) {
        this.taggerScoreList = taggerScoreList != null ? taggerScoreList : new ArrayList<>();
    }

    public List<Score> getTaggerScoreList() {
        return taggerScoreList;
    }
}
