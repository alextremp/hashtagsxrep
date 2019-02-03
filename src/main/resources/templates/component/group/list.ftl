<div class="ht-box">
    <div class="ht-tip">
        <#if !(groupList?size == 0)>
        Llista de grups.
        <#else>
        No hi ha grups.
        </#if>
    </div>
    <div class="ht-monitors">
        <#list groupList as group>
        <div class="ht-monitor">
            <a href="/group/${group.id}" class="button button-ht">
                ${group.id}
            </a>
        </div>
        </#list>
    </div>
</div>
