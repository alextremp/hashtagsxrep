<div class="ht-tip">
    Resultat de la votació. (${loadPollResponse.pollVoteCount} vots)
</div>
<#list loadPollResponse.pollProposals as proposal>
    <#if proposal?counter == 1>
    <div class="ht-proposal-wbox winner">
    <header>Proposta Guanyadora</header>
    <#if loadPollResponse.draw><div class="ht-tip"><i class="fas fa-info-circle"></i> En cas d'empat, guanya la proposta feta per l'usuari millor posicionat al nostre rànquing.</div></#if>
    <#elseif proposal?counter == 2>
    <div class="ht-proposal-wbox others">
        <header>Resta de propostes</header>
    </#if>
    <div class="ht-proposal <#if proposal.cancelationReason??>ht-proposal-cancelled</#if>">
        <header>${proposal.hashtag}</header>
        <div class="ht-vote-info"><i class="fas fa-user-tag"></i> @${proposal.authorNickname}&nbsp;&nbsp;<i class="fas fa-thumbs-up"></i>  ${proposal.votes} vots</div>
        <#if proposal.cancelationReason??>
            <div class="ht-proposal-cancel-reason"><b>L'administració de @HashtagsXRep ha cancel·lat aquesta proposta:</b> ${stringEscapeService.unescape(proposal.cancelationReason)}</div>
            <@security.authorize access="hasRole('ROLE_ADMIN')">
            <div class="ht-tip">Moderador: ${proposal.moderatorNickname}</div>
            </@security.authorize>
        </#if>
        <div class="ht-proposal-subject">${stringEscapeService.unescape(proposal.subject)}</div>
        <#if user.id == proposal.authorId>
        <footer>
            <div class="ht-tip">
                És la teva proposta.
            </div>
        </footer>
        <#elseif loadPollResponse.userVote?? \and loadPollResponse.userVote.authorId == proposal.authorId>
        <footer>
            <div class="ht-tip">
                Has votat aquesta proposta.
            </div>
        </#if>
    </footer>
    </div>
    <#if proposal?counter == 1>
    </div>
    <div class="ht-message">
        <i class="fas fa-clock"></i> Recorda, a les ${loadPollResponse.poll.asString(loadPollResponse.poll.startEventTime)} comencem!
    </div>
    <#if loadPollResponse.monitor??>
    <div class="ht-tip">
        <i class="fas fa-robot"></i> Aquí pots seguir l'evolució de l'atac:
    </div>
    <div class="ht-monitor">
        <a href="/report/${loadPollResponse.monitor.twitterQuery?replace('#', '')}" class="button button-ht">
            ${loadPollResponse.monitor.twitterQuery}
        </a>
    </div>
    </#if>
    </#if>
</#list>
<#if loadPollResponse.pollProposals?size == 2>
</div>
</#if>