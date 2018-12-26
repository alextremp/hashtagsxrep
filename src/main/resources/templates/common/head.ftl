<#--
HEAD COMPONENT: common content for <head> tag
- pageTitle
- pageDescription
- pageImage
- pageUrl
-->

<#if springMacroRequestContext.queryString??>
    <#assign qs = '?'+springMacroRequestContext.queryString />
<#else>
    <#assign qs = '' />
</#if>
<#assign url = 'https://hashtagsxrep.herokuapp.com'+springMacroRequestContext.requestUri+qs />
<#assign defaultTitle = 'Hastags per la República' />
<#assign defaultDescription = 'Creació i analítica de Hashtags en pro de la República Catalana.' />
<#assign defaultImage = 'https://hashtagsxrep.herokuapp.com/ui/image/icon/apple-icon-180x180.png' />
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<meta http-equiv="X-UA-Compatible" content="ie=edge"/>
<title>${pageTitle!defaultTitle}</title>
<link rel="canonical" href="${url}">
<meta name="description" content="${pageDescription!defaultDescription}"/>
<meta property="og:title" content="${pageTitle!defaultTitle}"/>
<meta property="og:description" content="${pageDescription!defaultDescription}"/>
<meta property="og:image" content="${pageImage!defaultImage}"/>
<meta property="og:url" content="${pageUrl!url}"/>
<meta property="og:site_name" content="Hashtags per la República"/>
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:url" content="${url}">
<meta name="twitter:title" content="${pageTitle!defaultTitle}">
<meta name="twitter:description" content="${pageDescription!defaultDescription}">
<meta name="twitter:image:src" content="${pageImage!defaultImage}">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
<link rel="apple-touch-icon" href="/ui/image/icon/apple-icon-180x180.png"/>
<link rel="icon" type="image/png" href="/ui/image/icon/android-icon-192x192.png"/>
<link rel="manifest" href="/ui/manifest.json"/>
<meta name="msapplication-TileColor" content="#ffffff"/>
<meta name="msapplication-TileImage" content="/ui/image/icon/ms-icon-144x144.png"/>
<meta name="theme-color" content="#ffffff"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css"/>
<link rel="stylesheet" href="/ui/css/main.css"/>
<script defer src="https://use.fontawesome.com/releases/v5.6.3/js/all.js" integrity="sha384-EIHISlAOj4zgYieurP0SdoiBYfGJKkgWedPHH4jCzpCXLmzVsw1ouK59MuUtP4a1" crossorigin="anonymous"></script>