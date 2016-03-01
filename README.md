# Service backend


Is [Play!](https://www.playframework.com/) application in version 2.4.

## Usage

is used as microservice in [play-main-microservice-arch](https://github.com/peterszatmary/play-main-microservice-arch) project.

You can use this project also standalone or with other two

- [play-service-one](https://github.com/peterszatmary/play-service-one)
- [play-main-microservice-arch](https://github.com/peterszatmary/play-main-microservice-arch)

### Application.selectOneFriend(id : Long)
- GET  /backend/friend/:id  
- select one friend by id from mysql database
- check with ```curl -N -X GET http://localhost:{port here, probably 9000}/backend/friend/{id number}``` or with browser

```java
  public F.Promise<Result> selectOneFriend(long id) {
        return F.Promise.promise(() -> dao.selectOneFriend(id) ,jpaExecContext) // non-blocking with F.Promise.promise
                .map((x) -> x == null ? "" : x, jpaExecContext)
                .map(Json::toJson, jpaExecContext)
                .map(jsonResponse -> (Result) ok(jsonResponse), jpaExecContext)
                .recover(t -> badRequest(t.getMessage() + "\n"), jpaExecContext);
    }
```

### Application.deleteOneFriend(id : Long)
- DELETE        /backend/friend/:id    
- delete one friend with id from mysql database
- check with ```curl -N -X DELETE http://localhost:{port here, probably 9000}/backend/friend/{id number}```

```java
public F.Promise<Result> deleteOneFriend(long id) {
        return F.Promise.promise(
                () -> dao.deleteOneFriend(id) // Promise<Boolean>
                ,jpaExecContext
        ) // non-blocking with F.Promise.promise
                .map(x -> {
                    Map<String, Boolean> data = new HashMap<>();
                    data.put("result", x);
                    return data;
                }) // Map<String, Boolean>
                .map(Json::toJson) // JsonNode
                .map(jsonResponse -> (Result) ok(jsonResponse))
                .recover(t -> badRequest(t.getMessage() + "\n"));
    }
```

### Application.selectAllFriends()
- GET           /backend/friends        
- select all friends from mysql database
- check with ```curl -N -X GET http://localhost:{port here, probably 9000}/backend/friends``` or with browser

```java
  public F.Promise<Result> selectAllFriends() {
        return F.Promise.promise(() -> dao.selectAllFriends(),jpaExecContext) // non-blocking with F.Promise.promise
                .map(Json::toJson)
                .map(jsonResponse -> (Result) ok(jsonResponse))
                .recover(t -> badRequest(t.getMessage()  + "\n"));
    }
```

### Application.addOneFriend(id : Long)
- PUT           /backend/friend/:id
- add (create) one friend with id in mysql database
- check with ```curl -N -X PUT http://localhost:{port here, probably 9000}/backend/friend/{id number}```

```java
    public F.Promise<Result> addOneFriend(long id) {
        return F.Promise.promise(
                () -> dao.addOneFriend(new Friend(id)) // Promise<Boolean>
                ,jpaExecContext
        ) // non-blocking with F.Promise.promise
                .map(x -> {
                    Map<String, Boolean> data = new HashMap<>();
                    data.put("result", x);
                    return data;
                }) // Map<String, Boolean>
                .map(Json::toJson) // JsonNode
                .map(jsonResponse -> (Result) ok(jsonResponse))
                .recover(t -> badRequest(t.getMessage()  + "\n"));
    }
```

you can try with curl.

images here .... TODO ....


## Deployment

```shell
./activator stage
```

or with other projects

```shell
./activator stage -Dhttp.port=9002
```

or in development mode

```shell
./activator run
```

or with other projects

```shell
./activator run -Dhttp.port=9002
```


## Configuration

See **conf/mock.conf.**

```java
selectOne.delay=2000
selectOne.exception=true
selectAll.delay=4000
selectAll.exception=false
addOne.delay=6000
addOne.exception=false
deleteOne.delay=8000
deleteOne.exception=false 
```

As you see you can here change the delays for every single crud operation and also tell if ends with error or not.


TODO ... mysql ... data ... evolutions
