<#assign showProposalForm = loadPollResponse.poll.proposalsTime \and !(loadPollResponse.userProposal??) \and user.hasAccess('ADMIN') />
<#assign showUserProposed = loadPollResponse.poll.proposalsTime \and loadPollResponse.userProposal?? \and user.hasAccess('ADMIN') />
<#assign showProposalsList = loadPollResponse.poll.votingTime \and loadPollResponse.pollProposals?? \and user.hasAccess('TAGGER') />
<div class="ht-box">
    <#if showProposalForm>
    <div class="ht-tip">
        Proposa un hashtag.
    </div>
    <form action="/poll/${loadPollResponse.poll.id}/proposal" method="post" class="ht-form">
        <div class="row">
            <div class="twelve columns">
                <label for="hashtag">Hashtag</label>
                <input class="u-full-width" type="text" placeholder="#HashtagVàlid" id="hashtag" name="hashtag" required
                       pattern="#[A-Za-z0-9]{1,25}" title="#Hashtag d'entre 1 i 25 caràcters alfanumèrics"/>
            </div>
        </div>
        <div class="row">
            <div class="twelve columns">
                <label for="subject">Tema</label>
                <textarea class="u-full-width" id="subject" name="subject" required minlength="10" maxlength="2000" rows="4"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="twelve columns ht-submit-row">
                <button class="button button-primary" type="submit">
                    <i class="fas fa-plus-circle"></i> #Proposa
                </button>
            </div>
        </div>
    </form>
    <#elseif showUserProposed>
    <div class="ht-tip">
        La teva proposta.
    </div>
    <div class="ht-proposal">
        <header>${loadPollResponse.userProposal.hashtag}</header>
        <div>
            ${loadPollResponse.userProposal.subject}
        </div>
    </div>
    <#elseif showProposalsList>
    <div class="ht-tip">
        Llista de propostes. Només pots votar-ne una!
    </div>
    <form action="/poll/${loadPollResponse.poll.id}/vote" method="post" class="ht-form">
        <input type="hidden" name="vote" id="vote" value=""/>
        <#list loadPollResponse.pollProposals as proposal>
        <div class="ht-proposal">
            <header>${proposal.hashtag}</header>
            <div>
                ${proposal.subject}
                <footer>
                <#if !(user.id == proposal.authorId)>
                    <button type="submit" onclick="document.getElementById('vote').value='${proposal.authorId}'"><i class="fas fa-thumbs-up"></i> #Vota</button>
                <#else>
                    <div class="ht-tip">
                        És la teva proposta.
                    </div>
                </#if>
                </footer>
            </div>
        </div>
        </#list>
    </form>
    </#if>
</div>