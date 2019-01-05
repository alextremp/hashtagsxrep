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
                Aquesta aplicació està desplegada en una plataforma gratuita i és possible que s'hagin sobrepassat els usuaris connectats simultàneament als que pot donar-se abast.<br/><br/>
                També es pot deure a un bug (shit happens).<br/><br/>
                Prova d'accedir en una estona :)<br/><br/>
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
