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

    <#include "component/poll/delete.ftl">

    <#include "component/footer.ftl">
</div>
</body>
</html>