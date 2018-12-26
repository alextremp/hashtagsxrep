<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Inici | Hashtags per la República">
    <#include "common/head.ftl">
</head>
<body>
<div class="container">
    <#include "component/header.ftl">

    <@security.authorize access="! isAuthenticated()">
        <#include "component/user/login-with-twitter.ftl">
    </@security.authorize>

    <@security.authorize access="isAuthenticated()">
    <div class="ht-block ht-white-block ht-entry-block center">
        <div class="ht-info">
            <i class="fas fa-user-circle"></i>
            <@security.authentication property="principal.user.nickname"/>
        </div>
        <@security.authorize access="hasRole('ROLE_ADMIN')">
            <#include "component/monitor/page-link.ftl">
        </@security.authorize>
        <@security.authorize access="hasRole('ROLE_CREATOR')">
            <#include "component/poll/page-link.ftl">
        </@security.authorize>
    </div>
    </@security.authorize>
    <#include "component/footer.ftl">
</div>
</body>
</html>