# Address Book API

A REST API for managing address books and contacts

## How to run the application locally
1. spin up the test db using docker-compose

```bash
docker-compose up
```

2. run the application in intellij with `development` profile, this will expose 2 demo data endpoints for you:

this will load some test data for you
```http request
http://localhost:8090/address-book-service/init
```

this will clean up the database
```http request
http://localhost:8090/address-book-service/cleanup
```

## Swagger

API documentation is available via swagger `/address-book-service/swagger-ui.html`


## Running tests
- spin up the test db by running docker-compose up
- run build / test using intellij


## Deploying the application in a production environment
the application relies on a centralized mysql server (ie it is not included as part of the application)

1. build the spring boot application using intellij build

2. package the application with docker

```bash
./scripts/docker_build.sh
```
3. run the dockerized application as per normal:

```bash
docker run address-book-api
``` 

## Kill processes running on port
```bash
npx kill-port 8090
```