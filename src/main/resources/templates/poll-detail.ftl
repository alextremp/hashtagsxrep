<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Enquesta ${loadPollResponse.poll.description} | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<div>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-poll"></i> ${stringEscapeService.unescape(loadPollResponse.poll.description)}
        </div>

        <#include "component/poll/detail.ftl">

        <div class="ht-box">
            <#include "component/poll/page-link.ftl">
            <#include "component/monitor/page-link.ftl">
            <#include "component/home/page-link.ftl">
        </div>
    </div>

    <#if loadPollResponse.poll.authorId == user.id || user.role='CREATOR'>
    <div class="ht-delete">
        <div class="wrap-collabsible">
            <input id="poll-delete-collapser" class="toggle" type="checkbox"/>
            <label for="poll-delete-collapser" class="lbl-toggle">
                <i class="fas fa-trash-alt"></i> Esborra l'enquesta.
            </label>
            <div class="collapsible-content">
                <div class="content-inner">
                    <form action="/poll/${loadPollResponse.poll.id}/delete" method="post" class="ht-form">
                        <div class="row">
                            <div class="twelve columns">
                                <div class="ht-tip">
                                    <i class="fas fa-exclamation-triangle"></i> Esborrar l'enquesta és irreversible i eliminarà qualsevol proposta i tots els vots relacionats. Segur que vols fer-ho?
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