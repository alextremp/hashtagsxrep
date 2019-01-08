<div class="ht-box">

    <#if getTaggersRankingResponse.userRank.rank??>
    <div class="ht-tip">
        <i class="fas fa-heart"></i> Gràcies per participar!<br/>Aquesta és la teva posició al rànquing.
    </div>
    <header class="ht-user-rank">
        <i class="fas fa-medal"></i> #${getTaggersRankingResponse.userRank.rank} @${user.nickname}
    </header>
    <#else>
    <div class="ht-tip">
        No apareixes als nostres rànquings <i class="fas fa-sad-tear"></i>
    </div>
    </#if>
    <#if getTaggersRankingResponse.userRank.hashtagScoreList?size != 0>
    <div class="ht-tip">
        Desglòs de la teva puntuació.
    </div>
    <div class="ht-ranking">
        <header>${getTaggersRankingResponse.userRank.totalScore} punts</header>
        <#assign lastHT = ''/>
        <#list getTaggersRankingResponse.userRank.hashtagScoreList as score>
        <#if score.hashtag != lastHT>
        <div class="ht-score table-header">
            <div class="hashtag">${score.hashtag}</div>
            <div><i class="fas fa-grin-stars"></i></div>
        </div>
        </#if>
        <div class="ht-score">
            <div class="type"><span class="quantity">${score.quantity}</span> <#if score.type == 'T'>Tweets<#elseif score.type == 'Q'>Cites<#elseif score.type == 'C'>Comentaris<#else>Retweets</#if></div>
            <div class="score">${score.score}</div>
        </div>
        <#assign lastHT = score.hashtag/>
        </#list>
    </div>
    </#if>
</div>
