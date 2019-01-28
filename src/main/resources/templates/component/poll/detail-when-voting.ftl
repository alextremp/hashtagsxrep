<#if loadPollResponse.userVote??>
    <div class="ht-message mb20">
        <i class="fas fa-heart"></i> Gràcies pel teu vot! Torna a les ${loadPollResponse.poll.asString(loadPollResponse.poll.endVotingTime)} per poder conèixer la proposta de hashtag més votada!
    </div>
    <div class="ht-tip">
        Has votat: ${loadPollResponse.userVote.hashtag}
    </div>
    <div class="wrap-collabsible">
        <input id="unvote-collapser" class="toggle" type="checkbox"/>
        <label for="unvote-collapser" class="lbl-toggle">
            Desfés el teu vot.
        </label>
    <div class="collapsible-content">
        <div class="content-inner">
            <form action="/poll/${loadPollResponse.poll.id}/unvote" method="post" class="ht-form">
                <div class="ht-message">
                    Si desfàs el vot, tens fins les ${loadPollResponse.poll.asString(loadPollResponse.poll.endVotingTime)} per tornar a votar!
                </div>
                <div class="row">
                    <div class="twelve columns ht-submit-row">
                        <button class="button button-primary" type="submit">
                            <i class="fas fa-trash-alt"></i> #DesfésElVot
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>
</#if>
<#if loadPollResponse.pollProposals?? \and loadPollResponse.pollProposals?size != 0>
    <div class="ht-tip">
        Llista de propostes. (${loadPollResponse.pollVoteCount} vots)
    </div>
<#else>
    <div class="ht-tip">
        No hi ha propostes per aquesta enquesta.
    </div>
</#if>
<#if loadPollResponse.pollProposals?? \and loadPollResponse.pollProposals?size != 0>
    <form action="/poll/${loadPollResponse.poll.id}/vote" method="post" class="ht-form">
    <input type="hidden" name="proposalAuthorId" id="proposalAuthorId" value=""/>
        <#list loadPollResponse.pollProposals as proposal>
            <div class="ht-proposal <#if proposal.cancelationReason??>ht-proposal-cancelled</#if>">
                <header>${proposal.hashtag}</header>
                <div class="ht-proposal-action">
                    <#include "moderate-button.ftl"/>
                <#if !loadPollResponse.userVote?? \and user.canVote(proposal)>
                    <button type="submit" onclick="setValue('proposalAuthorId', '${proposal.authorId}')"><i class="fas fa-thumbs-up"></i> #Vota</button>
                </#if>
                </div>
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
                    </footer>
                </#if>
            </div>
        </#list>
    </form>
</#if>