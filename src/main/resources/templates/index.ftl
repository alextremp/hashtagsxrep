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

        <#if !user.inGroup>
        <div class="ht-box">
            <div class="ht-tip">
                Afegeix-te a un dels nostres grups de Twitter!
            </div>
            <form action="/group/users" method="post">
            <button class="button button-primary" type="submit">
                <i class="fas fa-star"></i> #AfegeixTe
            </button>
            </form>
        </div>
        </#if>

        <div class="ht-box">
            <#include "component/monitor/page-link.ftl">
            <#include "component/ranking/page-link.ftl">
            <#include "component/poll/page-link.ftl">
            <#include "component/about/page-link.ftl">
        </div>

        <@security.authorize access="hasRole('ROLE_ADMIN')">
        <div class="ht-box">
            <div class="ht-tip">Només visible per admins.</div>
            <#include "component/group/page-link.ftl">
        </div>
        </@security.authorize>
    </div>
    </@security.authorize>
    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
</body>
</html>