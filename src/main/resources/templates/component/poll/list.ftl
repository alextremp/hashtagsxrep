<div class="ht-box">
    <div class="ht-tip">
    <#if !(pollList?size == 0)>
        Últimes enquestes creades.
    <#else>
        Cap enquesta activa.
    </#if>
    </div>
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
