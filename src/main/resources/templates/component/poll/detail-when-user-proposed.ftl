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
                           pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{1,25}" title="#Hashtag d'entre 1 i 25 caràcters alfanumèrics"/>
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