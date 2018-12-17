<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Monitoritza | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="bg03">
    <div class="container">
        <#include "component/nav-menu.ftl">

            <@security.authorize access="! isAuthenticated()">
            <#include "component/user/login-with-twitter.ftl">
        </@security.authorize>

        <@security.authorize access="hasRole('ROLE_ADMIN')">
        <div class="row tm-mt-big">
            <div class="col-12 mx-auto tm-login-col">
                <div class="bg-white tm-block">
                    <#include "component/report/monitor-data.ftl">
                    <#include "component/report/extraction-data.ftl">
                </div>
            </div>
        </div>
        </@security.authorize>
        <#include "component/footer.ftl">
    </div>
</div>
</body>
</html>