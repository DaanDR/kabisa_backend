
## Kabisa web-service demo

Demo project for kabisa coding assignment.


### Frameworks/tools used

* Java 17
* Maven
* Postgres
* Spring boot 
* Webflux
* Liquibase
* Sleuth
* Lombok

Testing frameworks
* Spock
* WireMock
* Testcontainers

### Installation

_How to run the application._

1. Make sure Docker desktop is running
2. Build jar
   ```sh
   clean package
   ```
3. Build and run docker images
   ```sh
   docker-compose up
   ```

### After installing


Api consists of **REST** and **graphql** endpoints.

REST endpoint documentation can be found at : 
``http://localhost:8080/api/webjars/swagger-ui/index.html#/``

Graphql endpoint (``localhost:8080/graphql``) can be called with example query:

```
query{
    getAllQuotes{
        author
        quote
    }
}
```

### Sidenote: 
DB is empty on initialization, user first has to retrieve a random quote using the endpoint. \
Every time a random quote is retrieved, it is saved in the DB.

### Known issues: 
For some reason graphql controller keeps showing up in swagger ui. Using @Hidden does not seem to work. Seems te be a known issue :
https://stackoverflow.com/questions/71509914/how-to-disable-dgs-graphql-framework-controllers-in-swagger
