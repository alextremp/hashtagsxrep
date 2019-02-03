<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Error | Hashtags per la República">
    <#include "common/head.ftl">
</head>
<body>
<div class="container">
    <#include "component/header.ftl">
    <div class="ht-block ht-white-block ht-entry-block center">
        <div class="ht-info">
            <i class="fas fa-dizzy"></i> ERROR
        </div>
        <div class="ht-box">
            <div class="ht-tip">
                ${message}
            </div>
            <a href="${target}" class="button button-primary">
                <i class="fas fa-home"></i> #Torna
            </a>
        </div>
    </div>
    <#include "component/footer.ftl">
</div>
</body>
</html>
