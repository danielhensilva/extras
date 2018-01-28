#!/bin/bash

CONTAINER=dartgaming_database

docker ps -a
docker stop $CONTAINER
docker rm $CONTAINER
docker run -P --name $CONTAINER -e MYSQL_ROOT_PASSWORD=p4ss -e MYSQL_DATABASE=dartgaming -d mariadb
docker ps -a