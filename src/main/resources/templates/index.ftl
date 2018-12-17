<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Inici | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/nav-menu.ftl">

    <@security.authorize access="! isAuthenticated()">
        <#include "component/user/login-with-twitter.ftl">
    </@security.authorize>

    <@security.authorize access="isAuthenticated()">
    <div class="row tm-mt-big">
        <div class="col-12 mx-auto tm-login-col">
            <div class="bg-white tm-block">

        <#include "component/user/user-details.ftl">
        <@security.authorize access="hasRole('ROLE_ADMIN')">
            <#include "component/monitor/page-link.ftl">
        </@security.authorize>
        <@security.authorize access="hasRole('ROLE_CREATOR')">
            <#include "component/poll/page-link.ftl">
        </@security.authorize>

            </div>
        </div>
    </div>
    </@security.authorize>

    <#include "component/footer.ftl">
</div>
</body>
</html>
