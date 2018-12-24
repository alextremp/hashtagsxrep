<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Monitoritza | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/header.ftl">

    <@security.authorize access="isAuthenticated()">
    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-robot"></i> #Monitoritza
        </div>
        <@security.authorize access="hasRole('ROLE_ADMIN')">
            <#include "component/monitor/create-form.ftl">
        </@security.authorize>
    <#include "component/monitor/list.ftl">
    </div>
    </@security.authorize>
    <#include "component/footer.ftl">
</div>
</body>
</html>
