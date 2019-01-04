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
            <i class="fas fa-grin-beam-sweat"></i> NO TROBAT
        </div>
        <div class="ht-box">
            <div class="ht-tip">
                La pàgina no existeix.<br/><br/>
                Intenta accedir al contingut que buscaves navegant des de la pàgina d'inici.<br/><br/>
            </div>
            <a href="/" class="button button-primary">
                <i class="fas fa-home"></i> #Inici
            </a>
        </div>
    </div>
    <#include "component/footer.ftl">
</div>
</body>
</html>
