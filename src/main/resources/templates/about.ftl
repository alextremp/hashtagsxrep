<!DOCTYPE html>
<html lang="es">
<#include "common/enable-security.ftl">
<head>
    <#assign pageTitle = "Què fem | Hashtags per la República">
    <#assign pageDescription = "Defensem la República a la xarxa i promocionem la resistència incondicional a l'estat espanyol fins assolir la independència. Participa!">
    <#include "common/head.ftl">
</head>
<body>
<div class="container">
    <#include "component/header.ftl">

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-address-card"></i> #QuiSom
        </div>
        <div class="ht-block left about-text">
            Els administradors de <a href="https://twitter.com/HashtagsXRep" target="_blank">@HashtagsXRep</a> som un equip de voluntaris que ens vàrem proposar a l'estiu del 2018 defensar intensivament la #RepúblicaCatalana a la xarxa.<br/><br/>
            Creiem que la difusió de <b>missatges pro-república</b>, maximitzant els missatges a favor de Catalunya, així com els que puguin ser perjudicials per l'estat espanyol, i també els que s'originin per convocar accions que al principi poden no comptar amb el suport de partits o entitats, suposen un <b>benefici pel conjunt d'usuaris de la xarxa republicana.</b><br/><br/>
            <span class="italic">"...enlloc de a Twitter, hauríem d'estar al carrer..."</span><br/><br/>
            <i class="fas fa-info-circle"></i> L'iniciativa <a href="https://twitter.com/DivendresXRep" target="_blank">@DivendresXRep</a> des de la qual escollim un #hashtag temàtic cada divendres a les 21h va sorgir pensant amb les concentracions anti-feixistes del setembre 2018 a la Plaça de Sant Jaume (BCN), per informar i animar a tothom, nosaltres inclosos, a ser-hi presents per omplir la plaça per tancar l'accés als manifestants espanyols.<br/><br/>
            <b>La #RepúblicaCatalana cal defensar-la a tots els àmbits</b>, tant al carrer com a la xarxa!
        </div>
    </div>

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-broadcast-tower"></i> #QuèFem
        </div>
        <div class="ht-block left about-text">
            Un dels nostres objectius setmanals és <b>aconseguir fer una tendència</b> (trending topic) a Twitter, els divendres a les 21h mitjançant #hashtags.<br/><br/>
            En funció de l'actualitat de cada setmana, busquem:<br/><br/>

            <i class="fas fa-volume-up"></i> <b>DIFUSIÓ</b> del missatge pro-república.<br/>
            <i class="fas fa-eye"></i> <b>VISIBILITAT</b> de causes relacionades amb els presos polítics, ajuda humànitaria, drets humans, injustícia i barbàrie espanyola, etc.<br/>
            <i class="fas fa-globe-europe"></i> <b>INTERNACIONALITZACIÓ</b> de la causa, ja sigui col·laborant directament amb grups d'altres països, o intentant moure missatges fora de la nostra frontera que els puguin ser d'interès.<br/>
            <i class="fas fa-people-carry"></i> <b>MOBILITZACIÓ</b> a concentracions, aturades, vagues que puguin anar sorgint i siguin d'interès estratègic pel nostre territori.<br/><br/>

            L'objectiu final és mostrar i promocionar la <b>RESISTÈNCIA incondicional a l'estat espanyol fins que assolim la independència.</b>
        </div>
    </div>

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-volume-up"></i> #PerQuèFemTendències
        </div>
        <div class="ht-block left about-text">
            Aquesta és una mostra dels continguts que han sobrepassat la barrera de Twitter, a partir de #hashtags que hem originat des de <a href="https://twitter.com/DivendresXRep" target="_blank">@DivendresXRep</a>
        </div>
        <div id="news"></div>
        <div class="ht-block left about-text">
            Si contemplem Twitter com una eina que serveix per informar-se de manera interactiva, ens adonem que molta gent utilitza la Cerca de Twitter per saber sobre què s'està parlant a la seva zona, gent del seu entorn,... <br/>
            La pantalla de Cerca de Twitter, mostra les 5 primeres tendències, que "representa" que contenen les paraules clau sobre les que està parlant la gent.<br/>
            <b>I aquí és on veiem la importància de fer la tendència els divendres a les 21h:</b><br/>
            Quan un missatge aconsegueix posicionar-se al Top 5 de tendències, automàticament molta gent que entra a mirar sobre quins temes es parla, comença també a parlar sobre aquests temes. Si dins d'aquests temes hi ha missatges de resistència per la nostra part, a favor de la República Catalana, el fet de ser tendència provoca que s'expandeixi massivament durant uns dies.<br/>
            I com heu vist, fins i tot a mitjans de comunicació!
        </div>
    </div>

    <div class="ht-block ht-white-block center">
        <div class="ht-info">
            <i class="fas fa-users"></i> #Participa
        </div>
        <div class="ht-block left about-text">
            Participa als nostres atacs a la xarxa els divendres a les 21h!<br/>
            Des de <a href="https://twitter.com/DivendresXRep" target="_blank">@DivendresXRep</a> us animem a entrar a aquesta web on cada divendres:<br/>
            <i class="fas fa-clock"></i> A partir de les 17h podreu votar la proposta de hashtag a utilitzar.<br/>
            <i class="fas fa-clock"></i> A partir de les 20h podreu consultar la proposta guanyadora per començar a pensar els tweets a publicar.<br/>
            <i class="fas fa-clock"></i> A partir de les 21h ataquem la xarxa!<br/><br/>

            <b>#HashtagsXRep sou tots vosaltres!</b><br/><br/>

            <a href="https://twitter.com/DivendresXRep" target="_blank"><i class="fab fa-twitter"></i> @DivendresXRep a Twitter</a><br/>
            <a href="https://twitter.com/HashtagsXRep" target="_blank"><i class="fab fa-twitter"></i> @HashtagsXRep a Twitter</a><br/>
            <a href="https://t.me/HashtagsXRep" target="_blank"><i class="fab fa-telegram"></i> #HashtagsXRep a Telegram</a><br/>
        </div>
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