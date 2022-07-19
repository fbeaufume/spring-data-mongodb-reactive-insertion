# Spring Data MongoDB reactive insertion

This simple Spring Boot reactive application shows how to efficiently insert a `Flux` of items into MongoD.   

## Usage

First configure the MongoDB URL in `src/main/resources/application.properties`.

Then run the application with `mvn spring-boot:run` or using your IDE.
`java -jar target/spring-graphql-sample.jar`).

Then use the application URLs:
- http://localhost:8080/count-items counts the items in the database
- http://localhost:8080/insert-items?count=6 to insert 6 items in the database using the default method
- http://localhost:8080/insert-items?count=6&batchSize=3&optimized=true to insert 6 items in batches of 3 using the optimized method
- http://localhost:8080/delete-items deletes all items from the database
