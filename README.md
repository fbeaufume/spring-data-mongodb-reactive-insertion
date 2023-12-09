# Spring Data MongoDB reactive insertion

This repository is a sample application for my [Spring Data MongoDB reactive performance tip](https://www.adeliosys.fr/articles/spring-data-mongo-reactive-performance-tip/) article.

This simple Spring Boot reactive application shows how to efficiently insert a `Flux` of items into MongoDB.   

## Usage

First configure the MongoDB URL in `src/main/resources/application.properties`.

Then run the application with `mvn spring-boot:run` or using your IDE.

Then use the application URLs:
- http://localhost:8080/count-items counts the items in the database
- http://localhost:8080/insert-items?count=6 to insert 6 items in the database using the default method
- http://localhost:8080/insert-items?count=6&batchSize=3&optimized=true to insert 6 items in batches of 3 using the optimized method
- http://localhost:8080/delete-items deletes all items from the database
