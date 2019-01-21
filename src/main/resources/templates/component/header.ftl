<ul class="ht-slideshow">
    <li><span>Image 01</span></li>
    <li><span>Image 02</span></li>
    <li><span>Image 03</span></li>
    <li><span>Image 04</span></li>
    <li><span>Image 05</span></li>
    <li><span>Image 06</span></li>
</ul>
<header class="ht-header ht-block ht-white-block">
    <a href="/">
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
    </a>
</header>