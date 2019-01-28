<@security.authorize access="hasRole('ROLE_ADMIN')">
<button type="button" onclick="go('/poll/${loadPollResponse.poll.id}/moderate/${proposal.authorId}')"><i class="fas fa-skull-crossbones"></i> #Modera</button>
</@security.authorize>
