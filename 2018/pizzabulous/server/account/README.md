# Pizzabulous - Account management service

## Requirements
* Docker + compose
* NodeJS

## Setup for development

Initialise a new MariaDB using Docker:
```
docker run --name mariadb -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=pizzabulous-account -p 3306:3306 -d mariadb
```

Run application:
```
node index.js
```

## Endpoints

Check `index.rest` file.

## Execute tests

TBD

## Deployment

TBD