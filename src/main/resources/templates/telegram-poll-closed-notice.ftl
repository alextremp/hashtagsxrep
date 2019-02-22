<b>${stringEscapeService.unescapeHTML(notice.title)} a les ${notice.hour}</b>

${notice.hashtag}

<b>TEMA:</b>

${stringEscapeService.unescapeHTML(notice.subject)}

<b>INSTRUCCIONS:</b>

&#x2611; Prepara tweets amb el hashtag escollit i publica'ls a partir de les ${notice.hour}. Pots desar-los com a borrador o utilitzar eines com TweetDeck per tenir-los llestos per l'hora de l'atac!

&#x1F558; A LES ${notice.hour} &#x203c;

&#x2611; Fes RT a tweets amb el hashtag per ampliar-ne la difusió! Clicant sobre el hashtag podràs veure els tweets relacionats.

&#x1F6AB; No repeteixis el text dels teus tweets, ni afegeixis només el hashtag sense més text als teus tweets, Twitter ho penalitza.

&#x26a0; Comenteu tweets dels usuaris que seguiu afegint el hashtag! Així acabarem sent més!

&#x2139; El hashtag ha estat escollit a aquesta enquesta:
https://hashtagsxrep.herokuapp.com/poll/${notice.pollId}

&#x2139; Pots seguir l'evolució del hashtag aquí:
https://hashtagsxrep.herokuapp.com/report/${notice.hashtag?replace('#', '')}

&#x2139; RECORDA: a les ${notice.hour} comencem!!*!!

${notice.hashtag}

Salut i República &#x1f49b;