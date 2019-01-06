<div class="ht-box">
    <div class="ht-ranking">
        <div class="ht-score table-header">
            <div><i class="fas fa-medal"></i></div>
            <div><i class="fas fa-user-tag"></i></div>
            <div><i class="fas fa-grin-stars"></i></div>
        </div>
        <#list ranking.taggerScoreList as rank>
        <div class="ht-score">
            <div class="rank">#${rank?counter}</div>
            <div class="nickname"><a href="https://twitter.com/${rank.nickname}" target="_blank">@${rank.nickname}</a></div>
            <div class="score">${rank.score}</div>
        </div>
        </#list>
    </div>
</div>
