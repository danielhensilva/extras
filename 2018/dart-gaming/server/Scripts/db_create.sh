#!/bin/bash

CONTAINER=dartgaming_database

docker ps -a
docker stop $CONTAINER
docker rm $CONTAINER
docker run --name $CONTAINER -e MYSQL_ROOT_PASSWORD=p4ss mysql
docker ps -a