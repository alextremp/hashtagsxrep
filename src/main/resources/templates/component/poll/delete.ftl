<#if loadPollResponse.poll.authorId == user.id || user.role='CREATOR'>
<div class="ht-delete">
    <div class="wrap-collabsible">
        <input id="poll-delete-collapser" class="toggle" type="checkbox"/>
        <label for="poll-delete-collapser" class="lbl-toggle">
            <i class="fas fa-trash-alt"></i> Esborra l'enquesta.
        </label>
        <div class="collapsible-content">
            <div class="content-inner">
                <form action="/poll/${loadPollResponse.poll.id}/delete" method="post" class="ht-form">
                    <div class="row">
                        <div class="twelve columns">
                            <div class="ht-tip mb20">
                                <i class="fas fa-exclamation-triangle"></i> Esborrar l'enquesta és irreversible i eliminarà qualsevol proposta i tots els vots relacionats. Segur que vols fer-ho?
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="twelve columns">
                            <label for="description">Còpia la descripció de l'enquesta</label>
                            <input placeholder="Validació de seguretat..." id="description" name="description" type="text" minlength="3" maxlength="200" title="Breu descripció de l'atac pel que es fa l'enquesta." required class="u-full-width"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="twelve columns ht-submit-row">
                            <button class="button button-primary" type="submit">
                                <i class="fas fa-check-circle"></i> #Esborra
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</#if>