<#assign showNotStarted = loadPollResponse.poll.notStarted />
<#assign showProposalForm = loadPollResponse.poll.proposalsTime \and !(loadPollResponse.userProposal??) \and user.canPropose() />
<#assign showUserProposed = loadPollResponse.poll.proposalsTime \and loadPollResponse.userProposal?? \and user.canPropose() />
<#assign showCannotPropose = loadPollResponse.poll.proposalsTime \and  !user.canPropose() />
<#assign showProposalToVoteList = loadPollResponse.poll.votingTime />
<#assign showVoteResults = loadPollResponse.poll.votingClosed />
<div class="ht-box">
    <div class="ht-poll-clocks">
        <div class="ht-poll-clocks-data left m-auto">
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.startProposalsTime)}: #IniciPropostesDeHashtags</div>
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)}: #IniciVotacióDePropostes</div>
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.endVotingTime)}: #TancamentVotació</div>
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.startEventTime)}: #IniciAtac</div>
            <footer>
                <i class="fas fa-exclamation-triangle"></i> No utilitzeu el hashtag abans de l'#IniciAtac.
            </footer>
        </div>
    </div>
</div>
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
                    <textarea class="u-full-width" id="subject" name="subject" required minlength="20" maxlength="400" rows="4" title="Tema del hashtag, entre 20 i 400 caràcters"></textarea>
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
        <div class="ht-proposal">
            <header>${loadPollResponse.userProposal.hashtag}</header>
            <div class="ht-proposal-subject">
                ${loadPollResponse.userProposal.subject}
            </div>
            <footer>
                <div class="ht-tip">
                    És la teva proposta.
                </div>
            </footer>
        </div>
    <#elseif showNotStarted>
        <div class="ht-tip">
            <i class="fas fa-info-circle"></i> L'enquesta no s'inicia fins l'hora marcada amb #IniciPropostesDeHashtags.
        </div>
    <#elseif showCannotPropose>
        <div class="ht-tip">
            <i class="fas fa-info-circle"></i> De moment, només els administradors poden proposar hashtags. Torna a l'hora #IniciVotacióDePropostes per poder votar el teu hashtag preferit.
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
                <#if !loadPollResponse.userVote?? \and user.canVote(proposal)>
                <button type="submit" onclick="document.getElementById('proposalAuthorId').value='${proposal.authorId}'"><i class="fas fa-thumbs-up"></i> #Vota</button>
                </#if>
                <div class="ht-proposal-subject">${proposal.subject}</div>
                <#if user.id == proposal.authorId>
                <footer>
                    <div class="ht-tip">
                        És la teva proposta.
                    </div>
                </footer>
                <#elseif loadPollResponse.userVote?? \and loadPollResponse.userVote.authorId == proposal.authorId>
                <footer>
                    <div class="ht-tip">
                        Has votat aquesta proposta.
                    </div>
                </footer>
                </#if>
            </div>
            </#list>
        </form>
        </#if>
    <#elseif showVoteResults>
        <div class="ht-tip">
            Resultat de la votació. (${loadPollResponse.pollVoteCount} vots)
        </div>
        <#list loadPollResponse.pollProposals as proposal>
        <div class="ht-proposal">
            <header>${proposal.hashtag}</header>
            <div class="ht-vote-info"><i class="fas fa-user-tag"></i> @${proposal.authorNickname}&nbsp;&nbsp;<i class="fas fa-thumbs-up"></i>  ${proposal.votes} vots</div>
            <div class="ht-proposal-subject">${proposal.subject}</div>
            <#if user.id == proposal.authorId>
            <footer>
                <div class="ht-tip">
                    És la teva proposta.
                </div>
            </footer>
            <#elseif loadPollResponse.userVote?? \and loadPollResponse.userVote.authorId == proposal.authorId>
            <footer>
                <div class="ht-tip">
                    Has votat aquesta proposta.
                </div>
            </#if>
            </footer>
        </div>
        </#list>
    </#if>
</div>