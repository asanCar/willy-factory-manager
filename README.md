# willy-factory-manager
A REST API for the HR department of Willy Wonka's factory.

# Steps to run 
1. Run docker-compose:
```
$ docker-compose up -d
```

2. Try out the REST API:
### POST
```
$ curl -X POST \
  http://localhost:8080/api/v1/oompas/ \
  -H 'Content-Type: application/json' \
  -d '{"name":"John","age":32,"job":"cooker"}'
```

### GET
Retrieve full list:
```
$ curl -X GET \
  http://localhost:8080/api/v1/oompas \
  -H 'Content-Type: application/json'
```
Retrieve only one resource:
```
$ curl -X GET \
  http://localhost:8080/api/v1/oompas/1 \
  -H 'Content-Type: application/json' 
```

### PUT
```
$ curl -X PUT \
  http://localhost:8080/api/v1/oompas/3 \
  -H 'Content-Type: application/json' \
  -d '{"name":"John","age":32,"job":"cooker"}'
```
