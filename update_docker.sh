#!/usr/bin/env bash

mvn clean install

cp ./target/*.jar ./docker/images/digiback/artifact/app.jar

docker login --username=$DOCKER_USRNAME --password=$DOCKER_PSWD

echo "Update and push digiback database"
cd docker/images/mysql/
docker build -t stellucidam/digiback-ddb:debug .
docker push stellucidam/digiback-ddb:debug

echo "Update and push digiback backend"
cd ../digiback/
docker build -t stellucidam/digiback-backend:debug .
docker push stellucidam/digiback-backend:debug
