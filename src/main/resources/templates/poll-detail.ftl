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
        <#include "component/invite/detail.ftl">

    </div>

    <#include "component/poll/delete.ftl">

    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
</body>
</html>