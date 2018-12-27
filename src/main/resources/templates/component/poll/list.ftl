<div class="ht-box">
    <#if !(pollList?size == 0) >
    <div class="ht-tip">
        Últimes enquestes creades.
    </div>
    </#if>
    <div class="ht-polls">
        <div class="flex-container">
            <#list pollList as poll>
            <div class="ht-poll">
                <a href="/poll/${poll.id}" class="button button-ht">
                    ${poll.description}
                </a>
            </div>
            </#list>
        </div>
    </div>
</div>
<@security.authorize access="hasRole('ROLE_ADMIN')">
    <#include "../../component/monitor/page-link.ftl">
</@security.authorize>