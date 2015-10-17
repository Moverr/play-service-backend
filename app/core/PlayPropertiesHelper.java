package core;

import play.Play;

import java.util.Map;

/**
 * Created by nue on 6.10.2015.
 */
public final class PlayPropertiesHelper {


    public static final Boolean getSelectOneException() {
        return Play.application().configuration().getBoolean("selectOne.exception");
    }

    public static final Long getSelectOneDelay() {
        return Play.application().configuration().getLong("selectOne.delay");
    }

    public static final Boolean getSelectAllException() {
        return Play.application().configuration().getBoolean("selectAll.exception");
    }

    public static final Long getSelectAllDelay() {
        return Play.application().configuration().getLong("selectAll.delay");
    }

    public static final Boolean getAddOneException() {
        return Play.application().configuration().getBoolean("addOne.exception");
    }

    public static final Long getAddOneDelay() {
        return Play.application().configuration().getLong("addOne.delay");
    }

    public static final Boolean getDeleteOneException() {
        return Play.application().configuration().getBoolean("deleteOne.exception");
    }

    public static final Long getDeleteOneDelay() {
        return Play.application().configuration().getLong("deleteOne.delay");
    }

    public static final Map<String, Object> getAll() {
        return Play.application().configuration().asMap();
    }

}
