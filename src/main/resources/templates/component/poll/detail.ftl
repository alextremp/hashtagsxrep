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
            <div><i class="fas fa-clock"></i> ${loadPollResponse.poll.asString(loadPollResponse.poll.endRankingTime)}: #TancamentRànquing</div>
            <footer>
                <i class="fas fa-exclamation-triangle"></i> No utilitzeu el hashtag abans de l'#IniciAtac.
            </footer>
        </div>
    </div>
</div>
<div class="ht-box">
    <#if showProposalForm>
        <#include "detail-when-form-proposal.ftl">
    <#elseif showUserProposed>
        <#include "detail-when-user-proposed.ftl">
    <#elseif showNotStarted>
        <div class="ht-message">
            <i class="fas fa-info-circle"></i> L'enquesta no s'inicia fins el ${loadPollResponse.poll.asString(loadPollResponse.poll.startProposalsTime)}.
        </div>
    <#elseif showCannotPropose>
        <div class="ht-message">
            <i class="fas fa-info-circle"></i> Avui no pots proposar hashtag, però torna el ${loadPollResponse.poll.asString(loadPollResponse.poll.endProposalsTime)} i podràs votar el que més t'agradi!
        </div>
    <#elseif showProposalToVoteList>
        <#include "detail-when-voting.ftl">
    <#elseif showVoteResults>
        <#include "detail-when-results.ftl">
    </#if>
</div>