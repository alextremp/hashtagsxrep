<header class="ht-header ht-block ht-white-block">
    <div class="ht-up">
        <div class="ht-up-img">
            <@security.authorize access="isAuthenticated()">
                <img src="<@security.authentication property="principal.user.profileImageUrl"/>">
            </@security.authorize>
            <@security.authorize access="! isAuthenticated()">
                <i class="fas fa-user-secret"></i>
            </@security.authorize>
        </div>
    </div>
    <div class="ht-title ht-txt-big text-in">
        <i class="fab fa-slack-hash"></i> HashtagsXRep
    </div>
</header>