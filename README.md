Demo for kabisa_backend web api.

How to run locally:

1. clean install
2. docker-compose up in root directory

Api consists of REST and graphql endpoints.

REST endpoint documentation can be found at : http://localhost:8080/api/webjars/swagger-ui/index.html#/

Graphql endpoint (localhost:8080/graphql) can be called with example query:

```
query{
    getAllQuotes{
        author
        quote
    }
}
```

Sidenote: 
DB is empty on initialization, user first has to retrieve a random quote using the endpoint. \
Every time a random quote is retrieved, it is saved in the DB.