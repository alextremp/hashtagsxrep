<div class="ht-box">
    <#if poll.proposalsTime>
    <div class="ht-tip">
        Proposa un hashtag.
    </div>
    <form action="/proposal" method="post" class="ht-form">
        <input id="pollId" name="pollId" type="hidden" value="${poll.id}"/>
        <div class="row">
            <div class="six columns">
                <label for="hashtag">Hashtag</label>
                <input class="u-full-width" type="text" placeholder="#HashtagVàlid" id="hashtag" name="hashtag" required
                       pattern="#[A-Za-z0-9]{1,25}" title="#Hashtag d'entre 1 i 25 caràcters alfanumèrics"/>
            </div>
            <div class="six columns">
                <button class="button button-primary" type="submit">
                    <i class="fas fa-plus-circle"></i> #Proposa
                </button>
            </div>
        </div>
    </form>
    <#elseif poll.votingTime>
    </#if>
</div>