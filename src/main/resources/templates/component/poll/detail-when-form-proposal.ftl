<#include "hashtag-info.ftl">
<form id="proposalForm" action="/poll/${loadPollResponse.poll.id}/proposal" method="post" class="ht-form" onsubmit="return submitProposal();">
<div class="row">
    <div class="twelve columns">
        <label for="hashtag">Hashtag</label>
        <input class="u-full-width" type="text" placeholder="#HashtagVàlid" id="hashtag" name="hashtag" required
               pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{1,25}" title="#Hashtag d'entre 1 i 25 caràcters alfanumèrics"/>
        <div id="hashtag_validation"></div>
    </div>
</div>
<div class="row">
    <div class="twelve columns">
        <label for="subject">Tema</label>
        <textarea class="u-full-width" id="subject" name="subject" required minlength="20" maxlength="400" rows="4" title="Tema del hashtag, entre 20 i 400 caràcters"></textarea>
    </div>
</div>
<div class="row">
    <div class="twelve columns ht-submit-row">
        <button class="button button-primary" type="submit">
            <i class="fas fa-plus-circle"></i> #Proposa
        </button>
    </div>
</div>
</form>