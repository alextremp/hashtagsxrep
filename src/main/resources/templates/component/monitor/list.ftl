<div class="ht-box">
    <div class="ht-tip">
    <#if !(monitorList?size == 0)>
        Últims monitors creats.
    <#else>
        Cap monitor actiu.
    </#if>
    </div>
    <div class="ht-monitors">
        <div class="flex-container">
            <#list monitorList as monitor>
            <div class="ht-monitor">
                <a href="/report/${monitor.twitterQuery?replace('#', '')}" class="button button-ht">
                    ${monitor.twitterQuery}
                </a>
            </div>
            </#list>
        </div>
    </div>
</div>
