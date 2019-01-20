package cat.xarxarepublicana.hashtagsxrep.application.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterSearchService;

public class HashtagCountUseCase {

    private final TwitterSearchService twitterSearchService;

    public HashtagCountUseCase(TwitterSearchService twitterSearchService) {
        this.twitterSearchService = twitterSearchService;
    }

    public String count(String hashtag) {
        int maxAcceptedTweets = 10;
        int countTweets = twitterSearchService.countTweets(hashtag, maxAcceptedTweets);
        if (countTweets > maxAcceptedTweets) {
            return hashtag + ": Aquest hashtag ja està en ús (més de 10 tweets la última setmana), proposa'n un que no s'estigui fent servir";
        }
        return null;
    }
}
