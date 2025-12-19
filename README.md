# Spring Data MongoDB reactive insertion

This repository is a sample application for my
[Spring Data MongoDB reactive performance tip](https://beaufume.fr/articles/spring-data-mongo-reactive-performance-tip/)
article.

This simple Spring Boot reactive application shows how to efficiently insert a `Flux` of items into MongoDB.

## Usage

To use a default local MongoDB server with Docker run `docker run -d -p 27017:27017 --name mongodb mongo`
then `docker stop mongodb` to stop it when done.

Configure the MongoDB URL in `src/main/resources/application.properties` or leave as is
for the default local MongoDB from Docker.

Run the application with `mvn spring-boot:run` or using your IDE.

The main application URLs:

- http://localhost:8080/count-items counts the items in the database
- http://localhost:8080/insert-items?count=5 to insert 5 items in the database using the default method
- http://localhost:8080/insert-items?count=5&batchSize=3&optimized=true to insert 5 items in batches of 3 using the
  optimized method
- http://localhost:8080/delete-items deletes all items from the database
