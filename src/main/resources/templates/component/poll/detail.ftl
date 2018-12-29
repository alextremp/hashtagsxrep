<#assign showProposalForm = loadPollResponse.poll.proposalsTime \and !(loadPollResponse.userProposal??) \and user.hasAccess('ADMIN') />
<#assign showUserProposed = loadPollResponse.poll.proposalsTime \and loadPollResponse.userProposal?? \and user.hasAccess('ADMIN') />
<#assign showProposalToVoteList = loadPollResponse.poll.votingTime />
<#assign showVoteResults = loadPollResponse.poll.votingClosed />
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
                           pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{1,25}" title="#Hashtag d'entre 1 i 25 caràcters alfanumèrics"/>
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
    <#elseif showProposalToVoteList>
        <div class="ht-tip">
            <#if loadPollResponse.pollProposals?? \and loadPollResponse.pollProposals?size != 0>
            Llista de propostes.
            <#else>
            No hi ha propostes per aquesta enquesta.
            </#if>
        </div>
        <#if loadPollResponse.pollProposals?? \and loadPollResponse.pollProposals?size != 0>
        <form action="/poll/${loadPollResponse.poll.id}/vote" method="post" class="ht-form">
            <input type="hidden" name="proposalAuthorId" id="proposalAuthorId" value=""/>
            <#list loadPollResponse.pollProposals as proposal>
            <div class="ht-proposal">
                <header>${proposal.hashtag}</header>
                <div>
                    ${proposal.subject}
                    <footer>
                    <#if !loadPollResponse.userVote?? \and user.canVote(proposal)>
                        <button type="submit" onclick="document.getElementById('proposalAuthorId').value='${proposal.authorId}'"><i class="fas fa-thumbs-up"></i> #Vota</button>
                    <#elseif user.id == proposal.authorId>
                        <div class="ht-tip">
                            És la teva proposta.
                        </div>
                    <#elseif loadPollResponse.userVote.authorId == proposal.authorId>
                        <div class="ht-tip">
                            Has votat aquesta proposta.
                        </div>
                    </#if>
                    </footer>
                </div>
            </div>
            </#list>
        </form>
        </#if>
    <#elseif showVoteResults>
        <div class="ht-tip">
            Resultat de la votació.
        </div>
        <#list loadPollResponse.pollProposals as proposal>
        <div class="ht-proposal">
            <header>${proposal.hashtag} (${proposal.votes} <i class="fas fa-thumbs-up"></i>)</header>
            <div>
                ${proposal.subject}
                <footer>
                    <#if user.id == proposal.authorId>
                    <div class="ht-tip">
                        És la teva proposta.
                    </div>
                    <#elseif loadPollResponse.userVote.authorId == proposal.authorId>
                    <div class="ht-tip">
                        Has votat aquesta proposta.
                    </div>
                    </#if>
                </footer>
            </div>
        </div>
        </#list>
    </#if>
</div>