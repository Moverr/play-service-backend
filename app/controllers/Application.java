package controllers;

import com.google.inject.Inject;
import core.BackendDao;
import models.Friend;
import play.libs.Akka;
import play.libs.F;
import play.libs.HttpExecution;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

import static core.PlayPropertiesHelper.*;

/**
 * simple rest CRUD standalone service / Play! app / non-blocking, functional
 * just BackendDao uses blocking / non functional EntityManager !!
 */
public class Application extends Controller {

    private static final ExecutionContext jpaExecContext;

    static {
        jpaExecContext = HttpExecution.fromThread(
                Akka.system().dispatchers().lookup("jpa-execution-context"));
    }

    @Inject
    private BackendDao dao;

    public F.Promise<Result> selectOneFriend(long id) {
        return F.Promise.promise(() -> dao.selectOneFriend(id) ,jpaExecContext) // non-blocking with F.Promise.promise
                .map((x) -> x == null ? "" : x, jpaExecContext)
                .map(Json::toJson, jpaExecContext)
                .map(jsonResponse -> (Result) ok(jsonResponse), jpaExecContext)
                .recover(t -> badRequest(t.getMessage() + "\n"), jpaExecContext);
    }

    public F.Promise<Result> selectAllFriends() {
        return F.Promise.promise(() -> dao.selectAllFriends(),jpaExecContext) // non-blocking with F.Promise.promise
                .map(Json::toJson)
                .map(jsonResponse -> (Result) ok(jsonResponse))
                .recover(t -> badRequest(t.getMessage()  + "\n"));
    }

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

    public Result showRunProperties() {
        return ok(
                "{\n" +
                        " Select one delay = " + getSelectOneDelay() + "\n" +
                        " Select one exception = " + getSelectOneException() + "\n" +
                        " Select all delay = " + getSelectAllDelay() + "\n" +
                        " Select all delay = " + getSelectAllException() + "\n" +
                        " Add one delay = " + getAddOneDelay() + "\n" +
                        " Add one exception = " + getAddOneException() + "\n" +
                        " Delete one delay = " + getDeleteOneDelay() + "\n" +
                        " Delete one exception = " + getDeleteOneException() + "\n" +
                        "}"
        );
    }
}