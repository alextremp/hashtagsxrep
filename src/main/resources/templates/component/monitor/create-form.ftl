<@security.authorize access="hasRole('ROLE_ADMIN')">
<div class="ht-box">
    <div class="wrap-collabsible">
        <input id="monitor-form-collapser" class="toggle" type="checkbox"/>
        <label for="monitor-form-collapser" class="lbl-toggle">
            Dóna d'alta un nou monitor de Hashtags per extreure resultats.
        </label>
        <div class="collapsible-content">
            <div class="content-inner">
                <form action="/monitor" method="post" class="ht-form">
                    <div class="row">
                        <div class="six columns">
                            <label for="twitterQuery">Hashtag</label>
                            <input class="u-full-width" type="text" placeholder="#HashtagAConsultar" id="twitterQuery" name="twitterQuery" required
                                   pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{3,21}" title="#Hashtag d'entre 3 i 21 caràcters alfanumèrics"/>
                        </div>
                        <div class="six columns">
                            <#assign startTime = .now>
                            <label for="startTime">Inici</label>
                            <input class="u-full-width" type="datetime-local" id="startTime" name="startTime" value="${(startTime?long + 1 * 3600000)?number_to_datetime?string['yyyy-MM-dd\'T\'HH:\'00\'']}" required/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="twelve columns ht-submit-row">
                            <button class="button button-primary" type="submit">
                                <i class="fas fa-plus-circle"></i> #Crea
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</@security.authorize>