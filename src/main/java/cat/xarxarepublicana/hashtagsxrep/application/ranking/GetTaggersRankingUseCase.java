package cat.xarxarepublicana.hashtagsxrep.application.ranking;

import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;

public class GetTaggersRankingUseCase {

    private final RankingRepository rankingRepository;

    public GetTaggersRankingUseCase(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }


    public GetTaggersRankingResponse getTaggersRanking() {
        Ranking ranking = rankingRepository.loadRanking();
        return new GetTaggersRankingResponse(ranking);
    }

    public static class GetTaggersRankingResponse {

        private final Ranking ranking;

        public GetTaggersRankingResponse(Ranking ranking) {
            this.ranking = ranking;
        }

        public Ranking getRanking() {
            return ranking;
        }
    }
}
