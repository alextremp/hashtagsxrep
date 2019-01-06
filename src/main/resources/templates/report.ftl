<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Resultats | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-chart-line"></i> ${monitor.twitterQuery}
        </div>
        <#include "component/report/extraction-data.ftl">

        <div class="ht-box">
            <#include "component/monitor/page-link.ftl">
            <#include "component/ranking/page-link.ftl">
            <#include "component/poll/page-link.ftl">
            <#include "component/home/page-link.ftl">
        </div>
    </div>

    <#include "component/monitor/delete.ftl">

    <#include "component/footer.ftl">
</div>
</body>
</html>