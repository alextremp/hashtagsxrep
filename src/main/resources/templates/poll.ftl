<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Enquestes | Hashtags per la República">
    <#include "common/head.ftl">
</head>

<div>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-poll"></i> #Decideix
        </div>
        <@security.authorize access="hasRole('ROLE_CREATOR')">
            <#include "component/poll/create-form.ftl">
        </@security.authorize>
        <@security.authorize access="hasRole('ROLE_TAGGER')">
            <#include "component/poll/list.ftl">
        </@security.authorize>
    </div>

    <#include "component/footer.ftl">
</div>
</body>
</html>