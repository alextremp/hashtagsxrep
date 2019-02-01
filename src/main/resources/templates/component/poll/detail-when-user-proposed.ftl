<div class="ht-tip mb20">
    <i class="fas fa-info-circle"></i> Hi ha ${loadPollResponse.pollProposals?size} propostes registrades.
</div>
<div class="ht-message">
<i class="fas fa-heart"></i> Gràcies per la teva proposta! Torna a les ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)} per poder votar entre les altres propostes!
</div>
<div class="wrap-collabsible">
<input id="proposal-update-collapser" class="toggle" type="checkbox"/>
<label for="proposal-update-collapser" class="lbl-toggle">
    Modifica la teva proposta.
</label>
<div class="collapsible-content">
    <div class="content-inner">
        <form id="proposalForm" action="/poll/${loadPollResponse.poll.id}/proposal" method="post" class="ht-form" onsubmit="return submitProposal();">
            <div class="ht-message">
                Si la vols modificar, tens fins les ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)} per fer-ho!
            </div>
            <#include "hashtag-info.ftl">
            <div class="row">
                <div class="twelve columns">
                    <label for="hashtag">Hashtag</label>
                    <input class="u-full-width" type="text" placeholder="#HashtagVàlid" id="hashtag" name="hashtag" required value="${loadPollResponse.userProposal.hashtag}"
                           pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{3,21}" title="#Hashtag d'entre 3 i 21 caràcters alfanumèrics"/>
                    <div id="hashtag_validation"></div>
                </div>
            </div>
            <div class="row">
                <div class="twelve columns">
                    <label for="subject">Tema</label>
                    <textarea class="u-full-width" id="subject" name="subject" required minlength="20" maxlength="400" rows="4" title="Tema del hashtag, entre 20 i 400 caràcters">${stringEscapeService.unescape(loadPollResponse.userProposal.subject)}</textarea>
                </div>
            </div>
            <div class="row">
                <div class="twelve columns ht-submit-row">
                    <button class="button button-primary" type="submit">
                        <i class="fas fa-edit"></i> #Modifica
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
<div class="ht-proposal">
    <header>${loadPollResponse.userProposal.hashtag}</header>
<div class="ht-proposal-subject">${stringEscapeService.unescape(loadPollResponse.userProposal.subject)}</div>
<footer>
    <div class="ht-tip">
        És la teva proposta.
    </div>
</footer>
</div>

<@security.authorize access="hasRole('ROLE_ADMIN')">
<div class="ht-moderate-proposed">
    <div class="ht-info">
        <i class="fas fa-skull-crossbones"></i> #Modera
        <div class="ht-tip">Només visible per administradors</div>
    </div>
    <#list loadPollResponse.pollProposals as proposal>
    <div class="ht-proposal <#if proposal.cancelationReason??>ht-proposal-cancelled</#if>">
        <header>${proposal.hashtag}</header>
        <div class="ht-proposal-action">
            <#include "moderate-button.ftl"/>
        </div>
        <div class="ht-vote-info"><i class="fas fa-user-tag"></i> @${proposal.authorNickname}</div>
        <#if proposal.cancelationReason??>
        <div class="ht-proposal-cancel-reason"><b>L'administració de @HashtagsXRep ha cancel·lat aquesta proposta:</b> ${stringEscapeService.unescape(proposal.cancelationReason)}</div>
        <div class="ht-tip">Moderador: ${proposal.moderatorNickname}</div>
        </#if>
        <div class="ht-proposal-subject">${stringEscapeService.unescape(proposal.subject)}</div>
        <#if user.id == proposal.authorId>
        <footer>
            <div class="ht-tip">
                És la teva proposta.
            </div>
        </footer>
        </#if>
    </div>
    </#list>
</div>
</@security.authorize>
