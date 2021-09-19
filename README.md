# Address Book Service

A REST API for managing address books and contacts

## How to run the application locally
run the application in intellij with `development` profile, this will expose 2 demo data endpoints for you:

this will load some test data for you
```http request
http://localhost:8090/address-book-service/init
```

this will clean up the database
```http request
http://localhost:8090/address-book-service/cleanup
```

You can then view the demo data
```http request
http://localhost:8090/address-book-service/address-books/
```

## Swagger

API documentation is available via swagger `/address-book-service/swagger-ui.html`


## Running tests
- run build / test using intellij / gradle plugin


## Deploying the application in a production environment
the application relies on a centralized mysql server (ie it is not included as part of the application)

1. build the spring boot application using intellij build

2. package the application with docker

```bash
./scripts/docker_build.sh
```
3. run the dockerized application as per normal:

```bash
docker run -p 8090:8090 address-book-service 
``` 

## Kill processes running on port
```bash
npx kill-port 8090
```