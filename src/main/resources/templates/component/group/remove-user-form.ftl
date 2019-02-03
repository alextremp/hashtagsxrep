<div class="ht-box">
    <div class="wrap-collabsible">
        <input id="remove-user-form-collapser" class="toggle" type="checkbox"/>
        <label for="remove-user-form-collapser" class="lbl-toggle">
            Elimina un usuari al grup.
        </label>
        <div class="collapsible-content">
            <div class="content-inner">
                <form action="/group/${group.id}/remove" method="post" class="ht-form">
                    <div class="row">
                        <div class="twelve columns">
                            <label for="remove-nickname">Nickname</label>
                            <input class="u-full-width" type="text" placeholder="@nickname" id="remove-nickname" name="nickname" required
                                   pattern="@[A-Za-z0-9_]{3,15}" title="@nickname d'un usuari"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="twelve columns ht-submit-row">
                            <button class="button button-primary" type="submit">
                                <i class="fas fa-trash-alt"></i> #Elimina
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>