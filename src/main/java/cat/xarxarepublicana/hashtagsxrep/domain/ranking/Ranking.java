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

    public Integer getRankByNickname(String nickname) {
        for (int i=0; i<taggerScoreList.size(); i++) {
            if (taggerScoreList.get(i).getNickname().equals(nickname)) {
                return i + 1;
            }
        }
        return null;
    }
}
