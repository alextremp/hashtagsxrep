package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.MonitorRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterSearchService;

public class ValidateHashtagUseCase {

    private final TwitterSearchService twitterSearchService;
    private final MonitorRepository monitorRepository;

    public ValidateHashtagUseCase(TwitterSearchService twitterSearchService, MonitorRepository monitorRepository) {
        this.twitterSearchService = twitterSearchService;
        this.monitorRepository = monitorRepository;
    }

    public String validate(String hashtag) {
        int maxAcceptedTweets = 10;
        int countTweets = twitterSearchService.countTweets(hashtag, maxAcceptedTweets);
        if (countTweets > maxAcceptedTweets) {
            return hashtag + ": Aquest hashtag ja està en ús (més de 10 tweets la última setmana), proposa'n un que no s'estigui fent servir";
        }

        if (monitorRepository.findByTwitterQuery(hashtag) != null) {
            return hashtag + ": Aquest hashtag ja s'ha utilitzat en atacs anteriors, proposa'n un que no haguem fet servir";
        }
        return null;
    }
}
