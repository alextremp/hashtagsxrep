<#assign startProposalsTime = .now>
<div class="ht-box">
<div class="ht-tip">
    Dóna d'alta una nova enquesta per a decidir hashtag.
</div>
<form action="/poll" method="post" class="ht-form">
    <div class="row">
        <div class="twelve columns">
            <label for="description">Descripció</label>
            <input placeholder="Hashtag per l'atac..." value="" id="description" name="description" type="text" minlength="3" maxlength="120" required class="u-full-width"/>
        </div>
    </div>
    <div class="row">
        <div class="six columns">
            <label for="startProposalsTime">
                Data per començar a proposar hashtags
            </label>
            <input id="startProposalsTime" name="startProposalsTime" type="datetime-local" required value="${startProposalsTime?string.iso_m_nz}" class="u-full-width"/>
        </div>
        <div class="six columns">
            <label for="endProposalsTime">
                Data per tancar les propostes i iniciar la votació
            </label>
            <input id="endProposalsTime" name="endProposalsTime" type="datetime-local" required  value="${(startProposalsTime?long + 2 * 3600000)?number_to_datetime?string.iso_m_nz}" class="u-full-width"/>
        </div>
    </div>
    <div class="row">
        <div class="six columns">
            <label for="endVotingTime">
                Data de tancament de la votació de hashtags
            </label>
            <input id="endVotingTime" name="endVotingTime" type="datetime-local" required value="${(startProposalsTime?long + 4 * 3600000)?number_to_datetime?string.iso_m_nz}" class="u-full-width"/>
        </div>
        <div class="six columns">
            <label for="startEventTime">
                Data d'inici de l'atac
            </label>
            <input id="startEventTime" name="startEventTime" type="datetime-local" required value="${(startProposalsTime?long + 5 * 3600000)?number_to_datetime?string.iso_m_nz}" class="u-full-width"/>
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