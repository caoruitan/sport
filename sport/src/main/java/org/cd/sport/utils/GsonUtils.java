package org.cd.sport.utils;

import java.lang.reflect.Type;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * gson工具类
 *
 * @author : liuyk
 */
public final class GsonUtils {

    private final static Gson gson = new Gson();

    private final static JsonParser parser = new JsonParser();

    /**
     * 将json字符串转换成javabean
     *
     * @param json json格式的字符串
     * @param clazz class类型
     * @return
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 将json字符串转换成javabean
     *
     * @param json json格式的字符串
     * @param type type类型
     * @return
     */
    public static <T> T toBean(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 将json对象转换成javabean
     *
     * @param json json对象
     * @param type type类型
     * @return
     */
    public static <T> T toBean(JsonObject json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 将json对象转换成javabean
     *
     * @param json json对象
     * @param clazz class类型
     * @return
     */
    public static <T> T toBean(JsonObject json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 将对象转成json格式字符串
     *
     * @param t 要转换的对象
     * @return
     */
    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }

    /**
     * 将对象转换成map
     *
     * @param t 要转换的对象
     * @return
     */
    public static <T> Map<String, String> toMap(T t) {
        return gson.fromJson(toJson(t), new TypeToken<Map<String, String>>() {}.getType());
    }

    /**
     * 将json字符串转换成json对象
     *
     * @param json
     * @return
     */
    public static JsonObject toJson(String json) {
        JsonElement element = parser.parse(json);
        if (element != null) {
            return element.getAsJsonObject();
        }
        return new JsonObject();
    }
}
