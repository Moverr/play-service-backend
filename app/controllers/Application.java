package controllers;


import com.google.inject.Inject;
import core.BackendDao;
import models.Friend;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * simple rest CRUD standalone service / Play! app / non-blocking, functional
 * just BackendDao uses blocking / non functional EntityManager !!
 */
public class Application extends Controller {

    @Inject
    private BackendDao dao;

    public F.Promise<Result> selectOneFriend(long id) {
        return F.Promise.promise(() -> dao.selectOneFriend(id, false, 0)) // non-blocking with F.Promise.promise
                .map((x) -> x == null ? "" : x)
                .map(Json::toJson)
                .map(jsonResponse -> (Result) ok(jsonResponse))
                .recover(t -> badRequest(t.getMessage() + "\n"));
    }

    public F.Promise<Result> selectAllFriends() {
        return F.Promise.promise(() -> dao.selectAllFriends(false, 0)) // non-blocking with F.Promise.promise
                .map(Json::toJson)
                .map(jsonResponse -> (Result) ok(jsonResponse))
                .recover(t -> badRequest(t.getMessage()  + "\n"));
    }

    public F.Promise<Result> addOneFriend(long id) {
        return F.Promise.promise(
                () -> dao.addOneFriend(new Friend(id), false, 0) // Promise<Boolean>
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


    public F.Promise<Result> deleteOneFriend(long id) {
        return F.Promise.promise(
                () -> dao.deleteOneFriend(id, false, 0) // Promise<Boolean>
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

}