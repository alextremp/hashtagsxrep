<div class="ht-box">
    <div class="ht-tip">
        Visualitza els resultats dels últims monitors creats.
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