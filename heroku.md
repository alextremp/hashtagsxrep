https://devcenter.heroku.com/articles/getting-started-with-java

heroku login 

heroku create hashtagsxrep

git push heroku master 
heroku ps:scale web=1  

heroku open -a hashtagsxrep

heroku logs --tail


mvn clean install
heroku local web

heroku config:add TZ="Europe/Madrid"
heroku config:get TZ



- All existing containers (not only running)

    - show only running
docker ps

    - show all containers
docker ps -a


Remove all containers with status=exited
when running a lot of container, can be case that exists list of containers which has status exited . Command bellow can help to remove it in one command

docker rm $(docker ps -q -f status=exited)


Stop all containers
    - will run stop only for active
docker stop $(docker ps -q)
    - will run stop for all
docker stop $(docker ps -aq)


Run and attach to container ( '--rm' delete container after exit)
Pay attention! Every time with using docker run will create new container with specified image. When you are using often docker run . It will become boring after stop and remove it. Use option --rm so container will removed after it finishes. it can make your life easy.

docker run -it --rm <image_name> /bin/ash


Pass environment variables to docker
In case if you have several env variables you can use option -eor --env as example bellow.

docker run -it -e TEST=1234 --env TEST1=3456 --rm alpine /bin/ash


See logs in container
you can check logs of container. Use the following:

docker logs -f <container_name>

#docker commands
alias dr='docker rm $(docker ps -aq)'
alias ds='docker stop $(docker ps -aq)'
alias di='docker images'
alias dri='docker rmi $(docker images -q)'
alias dsr='ds && dr'
alias dps='docker ps -a'
alias dcup='docker-compose up'


Remove container when 'docker-compose up’ doesn't work
If there is issue when docker-compose up like

docker-compose up
Recreating liquibase-container-1
liquibase-container | Unexpected error running Liquibase: liquibase.xml does not exist
you can remove container with using docker-compose

docker-compose rm liquibase
run only one container from docker-compose

docker-compose up liquibase
Stop, remove containers, remove images and networks what was created by “docker-compose up”
docker-compose -f docker-compose.yml down --rmi all