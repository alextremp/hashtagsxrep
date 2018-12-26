<div class="ht-box">
    <div class="ht-tip">
        Dóna d'alta un nou monitor de Hashtags per extreure resultats.
    </div>
    <form action="/monitor" method="post" class="ht-form">
        <div class="row">
            <div class="six columns">
                <label for="twitterQuery">Hashtag</label>
                <input class="u-full-width" type="text" placeholder="#HashtagAConsultar" id="twitterQuery" name="twitterQuery" required/>
            </div>
            <div class="six columns">
                <#assign startTime = .now>
                <label for="startTime">Inici</label>
                <input class="u-full-width" type="datetime-local" id="startTime" name="startTime" value="${startTime?string.iso_m_nz}" required/>
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