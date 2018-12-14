<div class="row mt-2">
    <div class="col-12 text-center">
        <#list monitorList as monitor>
        <div class="mt-3">
            <a href="/report/${monitor.twitterQuery?replace('#', '')}" class="btn btn-primary btn-lg" role="button">
                <i class="fas fa-2x fa-eye tm-site-icon"></i> ${monitor.twitterQuery}
            </a>
        </div>
        </#list>

        <div class="mt-3 tm-tip">
            Llistat de monitors actius.
        </div>
    </div>
</div>