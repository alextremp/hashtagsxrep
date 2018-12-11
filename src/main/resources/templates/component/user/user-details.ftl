<div class="tm-role-<@security.authentication property="principal.user.role" />">
    <div class="row">
        <div class="col-3 text-center">
            <img src="<@security.authentication property="principal.user.profileImageUrl" />">
        </div>
        <div class="col-9 text-left">
            <div class="tm-block-title mt-3">Hola @<@security.authentication property="principal.user.nickname" />!</div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 text-center">
            <div class="tm-block-title mt-3"><@security.authentication property="principal.user.name" /></div>
        </div>
    </div>
</div>