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
                        <input placeholder="Atac del dia..." value="Atac #${startProposalsTime?string['ddMMM']}" id="description" name="description" type="text" minlength="3" maxlength="200" title="Breu descripció de l'atac pel que es fa l'enquesta." required class="u-full-width"/>
                    </div>
                </div>
                <div class="row">
                    <div class="six columns">
                        <label for="startProposalsTime">
                            Inici de Propostes
                        </label>
                        <input id="startProposalsTime" name="startProposalsTime" type="datetime-local" required value="${startProposalsTime?string['yyyy-MM-dd\'T\'10:\'00\'']}" class="u-full-width"/>
                    </div>
                    <div class="six columns">
                        <label for="endProposalsTime">
                            Inici de la Votació
                        </label>
                        <input id="endProposalsTime" name="endProposalsTime" type="datetime-local" required value="${startProposalsTime?string['yyyy-MM-dd\'T\'17:\'00\'']}" class="u-full-width"/>
                    </div>
                </div>
                <div class="row">
                    <div class="six columns">
                        <label for="endVotingTime">
                            Tancament de la Votació
                        </label>
                        <input id="endVotingTime" name="endVotingTime" type="datetime-local" required value="${startProposalsTime?string['yyyy-MM-dd\'T\'20:\'00\'']}" class="u-full-width"/>
                    </div>
                    <div class="six columns">
                        <label for="startEventTime">
                            Inici de l'Atac
                        </label>
                        <input id="startEventTime" name="startEventTime" type="datetime-local" required value="${startProposalsTime?string['yyyy-MM-dd\'T\'21:\'00\'']}" class="u-full-width"/>
                    </div>
                </div>
                <div class="row">
                    <div class="six columns">
                        <label for="endRankingTime">
                            Tancament del Rànquing
                        </label>
                        <input id="endRankingTime" name="endRankingTime" type="datetime-local" required value="${(startProposalsTime?long + 48 * 3600000)?number_to_datetime?string['yyyy-MM-dd\'T\'23:\'59\'']}" class="u-full-width"/>
                    </div>
                    <div class="six columns">
                        <label for="type">
                            Tipus d'enquesta
                        </label>
                        <select id="type" name="type" required>
                            <option value="public">Pública amb convidats</option>
                            <option value="private">Privada per admins</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="six columns no-mobile">&nbsp;</div>
                    <div class="six columns ht-submit-row">
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
