<div class="ht-box">
    <div class="ht-info">
        <i class="fas fa-users"></i> #Usuaris convidats a proposar hashtags
    </div>
    <div class="ht-tip">
        <i class="fas fa-info-circle"></i> Tothom podrà votar la proposta que més li agradi!
    </div>
    <div class="row">
        <#if loadPollResponse.inviteGroup.topRankingList?size != 0>
        <div class="six columns">
            <div class="ht-invite">
                <header>
                    Top Taggers del Rànquing
                </header>
                <div class="ht-invite-list">
                    <#list loadPollResponse.inviteGroup.topRankingList as inviteS>
                    <div class="ht-invite-item"><a href="https://twitter.com/${inviteS.nickname}" target="_blank">@${inviteS.nickname}</a></div>
                    </#list>
                </div>
            </div>
        </div>
        </#if>
        <#if loadPollResponse.inviteGroup.randomRankingList?size != 0>
        <div class="six columns">
            <div class="ht-invite">
                <header>
                    Taggers Aleatoris del Rànquing
                </header>
                <div class="ht-invite-list">
                    <#list loadPollResponse.inviteGroup.randomRankingList as inviteR>
                    <div class="ht-invite-item"><a href="https://twitter.com/${inviteR.nickname}" target="_blank">@${inviteR.nickname}</a></div>
                    </#list>
                </div>
            </div>
        </div>
        </#if>
    </div>
    <div class="row">
        <#if loadPollResponse.inviteGroup.influenceList?size != 0>
        <div class="six columns">
            <div class="ht-invite">
                <header>
                    Top Influencers del Rànquing
                </header>
                <div class="ht-invite-list">
                    <#list loadPollResponse.inviteGroup.influenceList as inviteI>
                    <div class="ht-invite-item"><a href="https://twitter.com/${inviteI.nickname}" target="_blank">@${inviteI.nickname}</a></div>
                    </#list>
                </div>
            </div>
        </div>
        </#if>
        <#if loadPollResponse.inviteGroup.adminList?size != 0>
        <div class="six columns">
            <div class="ht-invite">
                <header>
                    Administradors #HashtagsXRep
                </header>
                <div class="ht-invite-list">
                    <#list loadPollResponse.inviteGroup.adminList as inviteA>
                    <div class="ht-invite-item"><a href="https://twitter.com/${inviteA.nickname}" target="_blank">@${inviteA.nickname}</a></div>
                    </#list>
                </div>
            </div>
        </div>
        </#if>
    </div>
    <div class="row">
        <#if loadPollResponse.inviteGroup.taggerList?size != 0>
        <div class="six columns">
            <div class="ht-invite">
                <header>
                    Col·laboradors VIP
                </header>
                <div class="ht-invite-list">
                    <#list loadPollResponse.inviteGroup.taggerList as inviteT>
                    <div class="ht-invite-item"><a href="https://twitter.com/${inviteT.nickname}" target="_blank">@${inviteT.nickname}</a></div>
                    </#list>
                </div>
            </div>
        </div>
        </#if>
    </div>
</div>
