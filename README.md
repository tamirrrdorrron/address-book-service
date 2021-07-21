# Address Book API

A REST API for managing address books and contacts

## How to run the application locally
- spin up the test db by running docker-compose up
- run the application in intellij with `development` profile

## Running tests
- spin up the test db by running docker-compose up
- run tests using intellij

## Deploying the application in a production environment
the application relies on a centralized mysql server (ie it is not included as part of the application)

build the spring boot application

```bash
./scripts/docker_build.sh
```
run the dockerized application as per normal:

```bash
docker run address-book-api
``` 