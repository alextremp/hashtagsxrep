<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Votació | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<div>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-poll"></i> #Decideix
        </div>

        <#include "component/poll/create-form.ftl">
        <#include "component/poll/list.ftl">

        <div class="ht-box">
            <#include "component/monitor/page-link.ftl">
            <#include "component/ranking/page-link.ftl">
            <#include "component/home/page-link.ftl">
        </div>
    </div>

    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
</body>
</html>