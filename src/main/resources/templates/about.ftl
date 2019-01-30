<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Sobre nosaltres | Hashtags per la República">
    <#include "common/head.ftl">
</head>
<body>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-address-card"></i> #QuiSom
        </div>
    </div>

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-broadcast-tower"></i> #QuèFem
        </div>
    </div>

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-volume-up"></i> #PerQuèHoFem
        </div>
        <div id="news"></div>
    </div>



    <@security.authorize access="! isAuthenticated()">
        <#include "component/user/login-with-twitter.ftl">
    </@security.authorize>
    <#include "component/footer.ftl">
</div>
<#include "common/scripts.ftl">
<script src="/ui/js/news.js"></script>
</body>
</html>