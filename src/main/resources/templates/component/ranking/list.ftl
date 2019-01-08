<div class="ht-box">
    <div class="ht-info">
        <i class="fas fa-users"></i> ${getTaggersRankingResponse.ranking.taggerScoreList?size} participants
    </div>
    <div class="ht-ranking">
        <header>Top 50</header>
        <div class="ht-score table-header">
            <div><i class="fas fa-medal"></i></div>
            <div><i class="fas fa-user-tag"></i></div>
            <div><i class="fas fa-grin-stars"></i></div>
        </div>
        <#list getTaggersRankingResponse.ranking.taggerScoreList as rank>
        <div class="ht-score">
            <div class="rank">#${rank?counter}</div>
            <div class="nickname"><a href="https://twitter.com/${rank.nickname}" target="_blank">@${rank.nickname}</a></div>
            <div class="score">${rank.score}</div>
        </div>
        <#if rank?counter &gt;= 50>
            <#break>
        </#if>
        </#list>
    </div>
</div>
