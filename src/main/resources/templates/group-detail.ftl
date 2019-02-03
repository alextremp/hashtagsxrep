<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "#${group.id} | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<body>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-sitemap"></i> #${group.id}
        </div>

        <#include "component/group/detail.ftl">

    </div>
    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
</body>
</html>
