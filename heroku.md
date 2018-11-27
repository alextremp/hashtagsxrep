https://devcenter.heroku.com/articles/getting-started-with-java

heroku login 

heroku create hashtagsxrep

git push heroku master 
heroku ps:scale web=1  

heroku open -a hashtagsxrep

heroku logs --tail


mvn clean install
heroku local web