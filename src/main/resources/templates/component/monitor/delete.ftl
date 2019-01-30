<#if monitor.authorId == user.id || user.role='CREATOR'>
<div class="ht-delete">
    <div class="wrap-collabsible">
        <input id="monitor-delete-collapser" class="toggle" type="checkbox"/>
        <label for="monitor-delete-collapser" class="lbl-toggle">
            <i class="fas fa-trash-alt"></i> Esborra el monitor.
        </label>
        <div class="collapsible-content">
            <div class="content-inner">
                <form action="/monitor/${monitor.id}/delete" method="post" class="ht-form">
                    <div class="row">
                        <div class="twelve columns">
                            <div class="ht-tip mb20">
                                <i class="fas fa-exclamation-triangle"></i> Esborrar el Monitor és irreversible i esborrarà també totes les dades llegides de Twitter. Segur que vols fer-ho?
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="three columns no-mobile">&nbsp;</div>
                        <div class="six columns">
                            <label for="hashtag">Còpia el hashtag del monitor</label>
                            <input class="u-full-width" type="text" placeholder="#ValidacióDeSeguretat" id="hashtag" name="hashtag" required
                                   pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{3,21}" title="#Hashtag d'entre 3 i 21 caràcters alfanumèrics"/>
                        </div>
                        <div class="three columns no-mobile">&nbsp;</div>
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