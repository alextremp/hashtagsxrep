<@security.authorize access="hasRole('ROLE_CREATOR')">
<div class="row mt-2">
    <div class="col-12 text-center">
        <div class="mt-3">
            <form action="/monitor" method="post" class="tm-edit-product-form">
                <div class="input-group mb-3">
                    <label for="twitterQuery" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">
                        Hashtag
                    </label>
                    <input placeholder="#hashtag" value="#" id="twitterQuery" name="twitterQuery" type="text"
                           class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7"/>
                </div>
                <div class="input-group mb-3">
                    <label for="startTime" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">
                        Inici
                    </label>
                    <input id="startTime" name="startTime" type="datetime-local"
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
            Dóna d'alta un nou monitor per extreure resultats d'un hashtag.
        </div>
    </div>
</div>
</@security.authorize>