var OmplimStJaume = {
    "title": "#OmplimStJaume: Los independentistas boicotean la manifestación en defensa del castellano en Barcelona",
    "description": "Los independentistas de Acampada por la Libertad ocupan la plaza en la que iba a terminar la marcha convocada por Hablamos Español.",
    "image": "http://s.libertaddigital.com/videos/476/279/en-directo-primera-manifestacion-contra-la-imposicion-linguistica-6066292-1.jpg",
    "url": "https://www.libertaddigital.com/espana/2018-09-16/los-independentistas-boicotean-la-manifestacion-convocada-por-hablamos-espanol-1276624961/"
};
var ForaXusmapol = {
   "title": "#ForaXusmapol: La imatge que volen",
   "description": "Cada vegada que una reivindicació se’ns escapa del guió, el gruix de l’independentisme corre cap al divan a demanar-se en quina mesura hem donat o no hem donat [a l’adversari] la imatge que volien.",
   "image": "https://imatges.vilaweb.cat/nacional/wp-content/uploads/2018/10/29-Set-01190850-1024x537.jpeg",
   "url": "https://www.vilaweb.cat/noticies/la-imatge-que-volen-mail-obert-opinio-marta-rojals/"
};
var PuigdemontNobel = {
    "title": "#PuigdemontNobel: esdevé trending topic",
    "description": "L'etiqueta #PuigdemontNobel ha esdevingut trending topic aquesta nit, la vigília que demà es conegui qui rebrà aquest premi.",
    "image": "https://www.elnacional.cat/uploads/s1/55/00/58/1/puigdemont-presentacio-llibre-efe_5_630x315.jpeg",
    "url": "https://www.elnacional.cat/ca/politica/puigdemontnobel-trending-topic_311163_102.html"
};
var CUPintocable = {
    "title": "#CUPintocable: la xarxa hi fa costat arran de les amenaces d'il·legalització",
    "description": "Recentment els dirigents del PP, Pablo Casado, i de Ciutadans, Albert Rivera, han amenaçat públicament la CUP d’il·legalitzar-la.",
    "image": "https://imatges.vilaweb.cat/nacional/wp-content/uploads/2017/09/20170920cat0158-20212357-1024x690.jpg",
    "url": "https://www.vilaweb.cat/noticies/cupintocable-la-xarxa-fa-costat-a-la-cup-davant-les-amenaces-dillegalitacio/"
};
var ComencaLaRevolta21D = {
  "title": "#ComençaLaRevolta21D: La predicción de Bea Talegón para el 21-D: \"Lo cambiará todo\"",
  "description": "La abogada Bea Talegón ha asegurado a través de las redes sociales que \"si Sánchez estuviera dispuesto a dialogar podría entender que el 21 cambiará todo\".",
  "image": "https://www.elnacional.cat/uploads/s1/35/38/36/2/beatriz-talegon-sergi-alcazar-09_5_630x315.jpeg",
  "url": "https://www.elnacional.cat/es/politica/bea-talegon-21-d_332823_102.html"
};
function addNews(parent, json) {
    var content = "<div class='ht-news-item'>"
        +"<div class='ht-news-title'>" + json.title + "</div>"
        +"<div class='ht-news-content'>"
            +"<div class='ht-news-image' style='background-image:url(" + json.image + ")'>"
                +"<div class='ht-news-description'>" + json.description + "</div>"
            +"</div>"
        +"</div>"
        +"<div class='ht-news-link'>" + json.url + "</div>"
    +"</div>";
    parent.append(content);
}
var startNewsID = setTimeout(function(){
    var news = $('#news')
    addNews(news, OmplimStJaume);
    addNews(news, ForaXusmapol);
    addNews(news, PuigdemontNobel);
    addNews(news, CUPintocable);
    addNews(news, ComencaLaRevolta21D);
}, 10);
