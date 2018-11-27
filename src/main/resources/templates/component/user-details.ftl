<div class="row tm-mt-big">
    <div class="col-12 mx-auto tm-login-col">
        <div class="bg-white tm-block">
            <div class="row">
                <div class="col-3 text-center">
                    <img src="<@security.authentication property="principal.user.profileImageUrl" />">
                </div>
                <div class="col-9 text-left">
                    <h2 class="tm-block-title mt-3">Hola @<@security.authentication property="principal.user.nickname" />!</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center">
                    <h2 class="tm-block-title mt-3"><@security.authentication property="principal.user.name" /></h2>
                </div>
            </div>
        </div>
    </div>
</div>