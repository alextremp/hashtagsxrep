<div class="ht-box">
    <div class="wrap-collabsible">
        <input id="add-user-form-collapser" class="toggle" type="checkbox"/>
        <label for="add-user-form-collapser" class="lbl-toggle">
            Afegeix un usuari al grup.
        </label>
        <div class="collapsible-content">
            <div class="content-inner">
                <form action="/group/${group.id}/add" method="post" class="ht-form">
                    <div class="row">
                        <div class="twelve columns">
                            <label for="add-nickname">Nickname</label>
                            <input class="u-full-width" type="text" placeholder="@nickname" id="add-nickname" name="nickname" required
                                   pattern="@[A-Za-z0-9_]{3,15}" title="@nickname d'un usuari"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="twelve columns ht-submit-row">
                            <button class="button button-primary" type="submit">
                                <i class="fas fa-plus-circle"></i> #Afegeix
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>