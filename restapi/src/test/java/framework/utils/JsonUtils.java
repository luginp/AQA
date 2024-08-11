package framework.utils;


import com.google.gson.Gson;

public class JsonUtils {
    public static String convertToJson(Object object) {
        return new Gson().toJson(object, object.getClass());
    }
}
