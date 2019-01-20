<ul class="ht-slideshow">
    <li><span>Image 01</span><div><h3>re·lax·a·tion</h3></div></li>
    <li><span>Image 02</span><div><h3>qui·e·tude</h3></div></li>
    <li><span>Image 03</span><div><h3>bal·ance</h3></div></li>
    <li><span>Image 04</span><div><h3>e·qua·nim·i·ty</h3></div></li>
    <li><span>Image 05</span><div><h3>com·po·sure</h3></div></li>
    <li><span>Image 06</span><div><h3>se·ren·i·ty</h3></div></li>
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