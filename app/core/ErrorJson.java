package core;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

/**
 * Created by nue on 6.10.2015.
 */
public class ErrorJson {

    // not used
    public static JsonNode get(Throwable throwable) {
        return Json.toJson(throwable);
    }
}
