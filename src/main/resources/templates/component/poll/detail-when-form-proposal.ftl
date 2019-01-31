<#include "hashtag-info.ftl">
<form id="proposalForm" action="/poll/${loadPollResponse.poll.id}/proposal" method="post" class="ht-form" onsubmit="return submitProposal();">
<div class="row">
    <div class="twelve columns">
        <label for="hashtag">Hashtag</label>
        <input class="u-full-width" type="text" placeholder="#HashtagVàlid" id="hashtag" name="hashtag" required
               pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{3,21}" title="#Hashtag d'entre 3 i 21 caràcters alfanumèrics"/>
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
<@security.authorize access="hasRole('ROLE_ADMIN')">
<div class="wrap-collabsible">
    <input id="poll-form-collapser" class="toggle" type="checkbox"/>
    <label for="poll-form-collapser" class="lbl-toggle">
        Proposa sense validar, només en cas de col·laboracions amb hashtag que vingui donat!
    </label>
    <div class="collapsible-content">
        <div class="content-inner">
            <div class="row">
                <div class="twelve columns ht-submit-row">
                    <div class="ht-tip">Només en cas de col·laboracions amb hashtag que vingui donat!</div>
                    <button class="button button-primary" type="submit" onclick="proposalWithNoValidation()">
                        <i class="fas fa-plus-circle"></i> #ProposaSenseValidar
                    </button>
                </div>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function proposalWithNoValidation() {
        $("#proposalForm").attr('onsubmit', '');
    }
</script>
</@security.authorize>
</form>