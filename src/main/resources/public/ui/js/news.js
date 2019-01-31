var OmplimStJaume = {
    "title": "#OmplimStJaume: Los independentistas boicotean la manifestación de Hablamos Español",
    "image": "/ui/image/news/omplimstjaume.jpg",
    "url": "https://www.libertaddigital.com/espana/2018-09-16/los-independentistas-boicotean-la-manifestacion-convocada-por-hablamos-espanol-1276624961/"
};
var ForaXusmapol = {
    "title": "#ForaXusmapol: La imatge que volen",
    "image": "/ui/image/news/foraxusmapol.jpg",
    "url": "https://www.vilaweb.cat/noticies/la-imatge-que-volen-mail-obert-opinio-marta-rojals/"
};
var PuigdemontNobel = {
    "title": "#PuigdemontNobel: esdevé trending topic",
    "image": "/ui/image/news/puigdemontnobel.jpg",
    "url": "https://www.elnacional.cat/ca/politica/puigdemontnobel-trending-topic_311163_102.html"
};
var CUPintocable = {
    "title": "#CUPintocable: la xarxa hi fa costat arran de les amenaces d'il·legalització",
    "image": "/ui/image/news/cupintocable.jpg",
    "url": "https://www.vilaweb.cat/noticies/cupintocable-la-xarxa-fa-costat-a-la-cup-davant-les-amenaces-dillegalitacio/"
};
var ComencaLaRevolta21D = {
    "title": "#ComençaLaRevolta21D: La predicción de Bea Talegón para el 21-D: \"Lo cambiará todo\"",
    "image": "/ui/image/news/comencalarevolta21d.jpg",
    "url": "https://www.elnacional.cat/es/politica/bea-talegon-21-d_332823_102.html"
};
function addNews(parent, json, idx) {
    var content = "<div class='ht-news-item it-" + idx + "'>"
        +"<div class='ht-news-content'>"
            +"<div class='ht-news-image' style='background-image:url(" + json.image + ")'>"
                +"<div class='ht-news-description'>" + json.title + "</div>"
            +"</div>"
            +"<div class='ht-news-link'><a href='" + json.url + "' target='_blank'><i class='fas fa-newspaper'></i> #ObrirNotícia</a></div>"
        +"</div>"
    +"</div>";
    parent.append(content);
}
var currentHtNewsItem = 0;
function showSlides() {
    console.log('>> slides');
  var items = $('.ht-news-item');
  items.css('display', 'none');
  currentHtNewsItem = (currentHtNewsItem + 1) % items.length;
  $('.it-' + currentHtNewsItem).css('display', 'block');
}
var startNewsID = setTimeout(function(){
    var news = $('#news')
    addNews(news, OmplimStJaume, 0);
    addNews(news, ForaXusmapol, 1);
    addNews(news, PuigdemontNobel, 2);
    addNews(news, CUPintocable, 3);
    addNews(news, ComencaLaRevolta21D, 4);
    showSlides();
    clearTimeout(startNewsID);
    setInterval(showSlides, 5000);
}, 10);



