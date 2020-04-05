package in.reqres;

import java.util.HashMap;
import java.util.Map;

public class DataMap {
    private static Map<String, Object> mapObject;

    public static Map<String, Object> mapObject() {
        if (mapObject == null)
            mapObject = new HashMap<>();
        return mapObject;
    }
}
