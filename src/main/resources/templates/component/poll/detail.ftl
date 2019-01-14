<#assign canPropose = loadPollResponse.inviteGroup.isInvited(user) />
<#assign showNotStarted = loadPollResponse.poll.notStarted />
<#assign showProposalForm = loadPollResponse.poll.proposalsTime \and !(loadPollResponse.userProposal??) \and canPropose />
<#assign showUserProposed = loadPollResponse.poll.proposalsTime \and loadPollResponse.userProposal?? \and canPropose />
<#assign showCannotPropose = loadPollResponse.poll.proposalsTime \and  !canPropose />
<#assign showProposalToVoteList = loadPollResponse.poll.votingTime />
<#assign showVoteResults = loadPollResponse.poll.votingClosed />
<div class="ht-box">
    <div class="ht-poll-clocks">
        <div class="ht-poll-clocks-data left m-auto">
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.startProposalsTime)}: #IniciPropostesDeHashtags</div>
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)}: #IniciVotacióDePropostes</div>
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.endVotingTime)}: #TancamentVotació</div>
            <div class="ht-poll-clocks-mark"><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.startEventTime)}: #IniciAtac</div>
            <footer>
                <i class="fas fa-exclamation-triangle"></i> No utilitzeu el hashtag abans de l'#IniciAtac.
            </footer>
        </div>
    </div>
</div>
<div class="ht-box">
    <#if showProposalForm>
        <div class="ht-tip">
            Proposa un hashtag. La votació de propostes serà anònima. Acabada la votació es mostrarà la teva proposta junt amb el teu nickname :)
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
        <div class="ht-tip mb20">
            <i class="fas fa-info-circle"></i> Hi ha ${loadPollResponse.pollProposals?size} propostes registrades.
        </div>
        <div class="ht-message">
            <i class="fas fa-heart"></i> Gràcies per la teva proposta! Torna a les ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)} per poder votar entre les altres propostes!
        </div>
        <div class="wrap-collabsible">
            <input id="proposal-update-collapser" class="toggle" type="checkbox"/>
            <label for="proposal-update-collapser" class="lbl-toggle">
                Modifica la teva proposta.
            </label>
            <div class="collapsible-content">
                <div class="content-inner">
                    <form action="/poll/${loadPollResponse.poll.id}/proposal" method="post" class="ht-form">
                        <div class="ht-message">
                            Si la vols modificar, tens fins les ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)} per fer-ho!
                        </div>
                        <div class="row">
                            <div class="twelve columns">
                                <label for="hashtag">Hashtag</label>
                                <input class="u-full-width" type="text" placeholder="#HashtagVàlid" id="hashtag" name="hashtag" required value="${loadPollResponse.userProposal.hashtag}"
                                       pattern="#[A-Za-z0-9àèòÀÈÒáéíóúÁÉÍÓÚñÑïüÏÜçÇ]{1,25}" title="#Hashtag d'entre 1 i 25 caràcters alfanumèrics"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="twelve columns">
                                <label for="subject">Tema</label>
                                <textarea class="u-full-width" id="subject" name="subject" required minlength="20" maxlength="400" rows="4" title="Tema del hashtag, entre 20 i 400 caràcters">${stringEscapeService.unescape(loadPollResponse.userProposal.subject)}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="twelve columns ht-submit-row">
                                <button class="button button-primary" type="submit">
                                    <i class="fas fa-edit"></i> #Modifica
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="ht-proposal">
            <header>${loadPollResponse.userProposal.hashtag}</header>
            <div class="ht-proposal-subject">${stringEscapeService.unescape(loadPollResponse.userProposal.subject)}</div>
            <footer>
                <div class="ht-tip">
                    És la teva proposta.
                </div>
            </footer>
        </div>
    <#elseif showNotStarted>
        <div class="ht-message">
            <i class="fas fa-info-circle"></i> L'enquesta no s'inicia fins el ${loadPollResponse.poll.asString(loadPollResponse.poll.startProposalsTime)}.
        </div>
    <#elseif showCannotPropose>
        <div class="ht-message">
            <i class="fas fa-info-circle"></i> Avui no pots proposar hashtag, però torna el ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)} i podràs votar el que més t'agradi!
        </div>
    <#elseif showProposalToVoteList>
        <#if loadPollResponse.userVote??>
        <div class="ht-message mb20">
            <i class="fas fa-heart"></i> Gràcies pel teu vot! Torna a les ${loadPollResponse.poll.asString(loadPollResponse.poll.endVotingTime)} per poder conèixer la proposta de hashtag més votada!
        </div>
        </#if>
        <#if loadPollResponse.pollProposals?? \and loadPollResponse.pollProposals?size != 0>
        <div class="ht-tip">
            Llista de propostes. (${loadPollResponse.pollVoteCount} vots)
        </div>
        <#else>
        <div class="ht-tip">
            No hi ha propostes per aquesta enquesta.
        </div>
        </#if>
        <#if loadPollResponse.pollProposals?? \and loadPollResponse.pollProposals?size != 0>
        <form action="/poll/${loadPollResponse.poll.id}/vote" method="post" class="ht-form">
            <input type="hidden" name="proposalAuthorId" id="proposalAuthorId" value=""/>
            <#list loadPollResponse.pollProposals as proposal>
            <div class="ht-proposal">
                <header>${proposal.hashtag}</header>
                <#if !loadPollResponse.userVote?? \and user.canVote(proposal)>
                <button type="submit" onclick="document.getElementById('proposalAuthorId').value='${proposal.authorId}'"><i class="fas fa-thumbs-up"></i> #Vota</button>
                </#if>
                <div class="ht-proposal-subject">${stringEscapeService.unescape(proposal.subject)}</div>
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
        <#if proposal?counter == 1>
        <div class="ht-proposal-wbox winner">
            <header>Proposta Guanyadora</header>
        <#elseif proposal?counter == 2>
        <div class="ht-proposal-wbox others">
            <header>Resta de propostes</header>
        </#if>
        <div class="ht-proposal">
            <header>${proposal.hashtag}</header>
            <div class="ht-vote-info"><i class="fas fa-user-tag"></i> @${proposal.authorNickname}&nbsp;&nbsp;<i class="fas fa-thumbs-up"></i>  ${proposal.votes} vots</div>
            <div class="ht-proposal-subject">${stringEscapeService.unescape(proposal.subject)}</div>
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
        <#if proposal?counter == 1>
        </div>
        <div class="ht-message">
            <i class="fas fa-clock"></i> Recorda, a les ${loadPollResponse.poll.asString(loadPollResponse.poll.startEventTime)} comencem!
        </div>
        </#if>
        </#list>
        <#if loadPollResponse.pollProposals?size == 2>
        </div>
        </#if>
    </#if>
</div>