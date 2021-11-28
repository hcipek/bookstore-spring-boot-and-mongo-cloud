# getir-java-case-study

## Database

- MongoDB Atlas Cloud Database Used.
- Given Connection url only available till 4 December 2021, so better be replace it with your own DB Connection Url.

## Getting Docker Image

### Creating Docker Image From Project;

- docker build -f Dockerfile -t getirapp:latest . (may require going to the root of project)

### Pulling Image from Docker Hub

- docker pull hakancipek/getirapp:latest

## How to Run Application

- Simply run the docker image.

### Alternative

-Run the GetirApplication.java under the path getir/src/main/java/com/challenge/getir

## Swagger:

- Available at http://[host]:[port]/ by default

### Example for default properties;

- http://localhost:9661/

## Postman

- https://www.getpostman.com/collections/edd1e977eb36f9ea6cfb
