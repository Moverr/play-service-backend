package core;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

/**
 * Created by peterszatmary on 6.10.2015.
 */
public class ErrorJson {

    // not used
    public static JsonNode get(final Throwable throwable) {
        return Json.toJson(throwable);
    }
}