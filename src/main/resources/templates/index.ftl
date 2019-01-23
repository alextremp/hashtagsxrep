<!DOCTYPE html>
<html lang="es">
<head>
    <#assign pageTitle = "Inici | Hashtags per la República">
    <#include "common/head.ftl">
</head>
<body>
<div class="container">
    <#include "component/header.ftl">

    <#include "component/user/login-with-twitter.ftl">

    <div class="ht-block ht-white-block ht-entry-block center">
        <div class="ht-info">
            <i class="fas fa-user-circle"></i> nickname
        </div>

        <div class="ht-box">
            <#include "component/monitor/page-link.ftl">
            <#include "component/ranking/page-link.ftl">
            <#include "component/poll/page-link.ftl">
        </div>
    </div>
    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
</body>
</html>