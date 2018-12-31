<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Resultats | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-chart-line"></i> ${monitor.twitterQuery}
        </div>
        <#include "component/report/extraction-data.ftl">

        <div class="ht-box">
            <#include "component/monitor/page-link.ftl">
            <#include "component/poll/page-link.ftl">
            <#include "component/home/page-link.ftl">
        </div>
    </div>

    <#if monitor.authorId == user.id || user.role='CREATOR'>
    <div class="ht-delete">
        <div class="wrap-collabsible">
            <input id="monitor-delete-collapser" class="toggle" type="checkbox"/>
            <label for="monitor-delete-collapser" class="lbl-toggle">
                <i class="fas fa-trash-alt"></i> Esborra el monitor.
            </label>
            <div class="collapsible-content">
                <div class="content-inner">
                    <form action="/monitor/${monitor.id}/delete" method="post" class="ht-form">
                        <div class="row">
                            <div class="twelve columns">
                                <div class="ht-tip">
                                    <i class="fas fa-exclamation-triangle"></i> Esborrar el Monitor és irreversible i esborrarà també totes les dades llegides de Twitter. Segur que vols fer-ho?
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="twelve columns ht-submit-row">
                                <button class="button button-primary" type="submit">
                                    <i class="fas fa-check-circle"></i> #Esborra
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </#if>

    <#include "component/footer.ftl">
</div>
</body>
</html>