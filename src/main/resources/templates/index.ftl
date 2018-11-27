<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Inici | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body class="bg03">
    <div class="container">
        <#include "component/nav-menu.ftl">
        <@security.authorize access="! isAuthenticated()">
            <#include "component/login-with-twitter.ftl">
        </@security.authorize>
        <@security.authorize access="isAuthenticated()">
            <#include "component/user-details.ftl">
        </@security.authorize>
        <#include "component/footer.ftl">
    </div>
</body>
</html>