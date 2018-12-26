<div class="ht-resume">
    <div class="row">
        <div class="six columns">
            <div class="ht-result-box">
                <header class="ht-result"><i class="fas fa-user-edit"></i> Continguts</header>
                <div class="ht-result">${report.tweetedContentResume.contentCount} Missatges</div>
                <div class="ht-result">${report.tweetedContentResume.tweets} Tweets</div>
                <div class="ht-result">${report.tweetedContentResume.quotes} Cites</div>
                <div class="ht-result">${report.tweetedContentResume.comments} Comentaris</div>
                <div class="ht-result">${report.tweetedContentResume.retweets} Retweets</div>
            </div>
        </div>
        <div class="six columns">
            <div class="ht-result-box">
                <header class="ht-result"><i class="fas fa-user-tag"></i> Usuaris</header>
                <div class="ht-result">${report.taggersCount} usuaris han participat</div>
                <div class="ht-result">${report.userContentResume.tweets} han tuitejat</div>
                <div class="ht-result">${report.userContentResume.quotes} han citat tweets</div>
                <div class="ht-result">${report.userContentResume.quotes} han citat tweets</div>
                <div class="ht-result">${report.userContentResume.retweets} han retuitejat</div>
                <div class="ht-result">${report.interactorResume.interactorCount} han influït a altres ${report.interactorResume.interactedCount}</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="six columns">
            <div class="ht-result-box highlight">
                <header class="ht-result"><i class="far fa-eye"></i> Visualitzacions: ${report.maxImpressions}</header>
            </div>
        </div>
        <div class="six columns">
            <div class="ht-result-box highlight">
                <header class="ht-result"><i class="fas fa-bullhorn"></i> Audiència: ${report.maxAudience}</header>
            </div>
        </div>
    </div>
</div>