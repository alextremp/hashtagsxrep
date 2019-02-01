<form id="moderateForm" action="/poll/${proposal.pollId}/moderate/${proposal.authorId}" method="post" class="ht-form">
<div class="row">
    <div class="twelve columns">
        <label for="hashtag">Hashtag</label>
        <input class="u-full-width" type="text" placeholder="#HashtagVàlid" id="hashtag" name="hashtag" required
               pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{3,21}" title="#Hashtag d'entre 3 i 21 caràcters alfanumèrics"
               value="${proposal.hashtag}"/>
    </div>
</div>
<div class="row">
    <div class="twelve columns">
        <label for="subject">Tema</label>
        <textarea class="u-full-width" id="subject" name="subject" required minlength="20" maxlength="400" rows="4" title="Tema del hashtag, entre 20 i 400 caràcters">${stringEscapeService.unescape(proposal.subject)}</textarea>
    </div>
</div>
<div class="row">
    <div class="twelve columns">
        <label for="cancelationReason">Motiu Cancel·lació</label>
        <div class="ht-tip left">Deixa el motiu de cancel·lació buit si només cal corregir faltes de la propsta. Omplir el motiu implica la cancel·lació de la proposta.</div>
        <div class="ht-tip left"><i class="fas fa-exclamation-triangle"></i> Avisa a: <a href="https://twitter.com/${proposal.authorNickname}" target="_blank">@${proposal.authorNickname}</a> en el cas d'informar d'un motiu de cancel·lació per que pugui modificar-la.</div>
        <textarea class="u-full-width" id="cancelationReason" name="cancelationReason" minlength="10" maxlength="400" rows="4" title="Motiu de la cancel·lació de la proposta">${stringEscapeService.unescape(proposal.cancelationReason)!''}</textarea>
    </div>
</div>
<div class="row">
    <div class="twelve columns ht-submit-row">
        <button class="button button-primary" type="button" onclick="go('/poll/${proposal.pollId}')">
            <i class="fas fa-times-circle"></i> #Cancel·la
        </button>
        <button class="button button-primary" type="submit">
            <i class="fas fa-skull-crossbones"></i> #Modera
        </button>
    </div>
</div>
</form>