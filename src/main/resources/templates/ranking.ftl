<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Ranking | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/header.ftl">

    <@security.authorize access="isAuthenticated()">
    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-list-ol"></i> #Rànquing
        </div>

        <#include "component/ranking/user-score.ftl">
        <#include "component/ranking/score-info.ftl">
        <#include "component/ranking/list.ftl">

        <div class="ht-box">
            <#include "component/monitor/page-link.ftl">
            <#include "component/poll/page-link.ftl">
            <#include "component/home/page-link.ftl">
        </div>

    </div>
    </@security.authorize>
    <#include "component/footer.ftl">
</div>
</body>
</html>
