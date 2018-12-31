<@security.authorize access="hasRole('ROLE_ADMIN')">
<#assign startProposalsTime = .now>
<#if createPollResponse??>
<div class="ht-box ht-ok">
Enquesta creada.
</div>
</#if>
<div class="ht-box">
<div class="wrap-collabsible">
    <input id="poll-form-collapser" class="toggle" type="checkbox"/>
    <label for="poll-form-collapser" class="lbl-toggle">
        Dóna d'alta una nova enquesta per a decidir hashtag.
    </label>
    <div class="collapsible-content">
        <div class="content-inner">
            <form action="/poll" method="post" class="ht-form">
                <div class="row">
                    <div class="twelve columns">
                        <label for="description">Descripció</label>
                        <input placeholder="Atac del dia..." value="Atac #${startProposalsTime?string['ddMMM']}" id="description" name="description" type="text" minlength="3" maxlength="30" title="Breu descripció de l'atac pel que es fa l'enquesta." required class="u-full-width"/>
                    </div>
                </div>
                <div class="row">
                    <div class="six columns">
                        <label for="startProposalsTime">
                            Inici: Propostes de hashtags
                        </label>
                        <input id="startProposalsTime" name="startProposalsTime" type="datetime-local" required value="${(startProposalsTime?long + 1 * 3600000)?number_to_datetime?string['yyyy-MM-dd\'T\'HH:\'00\'']}" class="u-full-width"/>
                    </div>
                    <div class="six columns">
                        <label for="endProposalsTime">
                            Inici: Votació de hashtags
                        </label>
                        <input id="endProposalsTime" name="endProposalsTime" type="datetime-local" required value="${(startProposalsTime?long + 3 * 3600000)?number_to_datetime?string['yyyy-MM-dd\'T\'HH:\'00\'']}" class="u-full-width"/>
                    </div>
                </div>
                <div class="row">
                    <div class="six columns">
                        <label for="endVotingTime">
                            Fi: Votació de hashtags
                        </label>
                        <input id="endVotingTime" name="endVotingTime" type="datetime-local" required value="${(startProposalsTime?long + 6 * 3600000)?number_to_datetime?string['yyyy-MM-dd\'T\'HH:\'00\'']}" class="u-full-width"/>
                    </div>
                    <div class="six columns">
                        <label for="startEventTime">
                            Inici: Atac
                        </label>
                        <input id="startEventTime" name="startEventTime" type="datetime-local" required value="${(startProposalsTime?long + 7 * 3600000)?number_to_datetime?string['yyyy-MM-dd\'T\'HH:\'00\'']}" class="u-full-width"/>
                    </div>
                </div>
                <div class="row">
                    <div class="twelve columns ht-submit-row">
                        <button class="button button-primary" type="submit">
                            <i class="fas fa-plus-circle"></i> #Crea
                        </button>
                    </div>
                </div>
                <div class="row">
                    <div class="twelve columns">
                        <div class="ht-tip">
                            Quan acabi la votació es podrà consultar el hashtag guanyador amb el tema proposat i es crearà un monitor de hashtags per a poder consultar l'efectivitat de l'atac.
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
</@security.authorize>
