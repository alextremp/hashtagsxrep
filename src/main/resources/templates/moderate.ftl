<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Modera | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/header.ftl">

    <@security.authorize access="isAuthenticated()">
    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-skull-crossbones"></i> #Modera
        </div>

        <#include "component/poll/moderate-form.ftl">
    </div>
    </@security.authorize>
    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
</body>
</html>
