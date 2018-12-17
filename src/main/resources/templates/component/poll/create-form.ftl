<#assign currentTime = .now>
        ${lastUpdated?string.iso}
<div class="row mt-2">
    <div class="col-12 text-center">
        <div class="mt-3">
            <form action="/poll" method="post" class="tm-edit-product-form">
                <div class="input-group mb-3">
                    <input placeholder="Descripció de l'enquesta..." value="" id="description" name="description" type="text" minlength="10" maxlength="120" required
                           class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7"/>
                </div>
                <div class="input-group mb-3">
                    <label for="startProposalsTime" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">
                        Data i hora d'inici per proposar hashtags
                    </label>
                    <input id="startProposalsTime" name="startProposalsTime" type="datetime-local" required
                           class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7"/>
                </div>
                <div class="input-group mb-3">
                    <label for="endProposalsTime" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">
                        Data i hora de tancament de propostes de hashtags
                    </label>
                    <input id="endProposalsTime" name="endProposalsTime" type="datetime-local" required
                           class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7"/>
                </div>
                <div class="input-group mb-3">
                    <label for="endVotingTime" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">
                        Data i hora de tancament de la votació de hashtags
                    </label>
                    <input id="endVotingTime" name="endVotingTime" type="datetime-local" required
                           class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7"/>
                </div>
                <div class="input-group mb-3">
                    <div class="ml-auto col-xl-8 col-lg-8 col-md-8 col-sm-7 pl-0">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-4x fa-plus-circle tm-site-icon"></i> #Crea
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="mt-3 tm-tip">
            Dóna d'alta una nova enquesta per a votar hashtags.
        </div>
    </div>
</div>