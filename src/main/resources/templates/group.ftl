<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "#TRAMS | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-sitemap"></i> #TRAMS
        </div>

        <#include "component/group/list.ftl">

    </div>
    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
</body>
</html>
