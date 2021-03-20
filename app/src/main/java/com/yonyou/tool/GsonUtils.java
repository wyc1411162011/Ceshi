package com.yonyou.tool;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GsonUtils {
    private static Gson mGson = new Gson();

    /**
     * 把json字符串转换为JavaBean
     * @param json json字符串
     * @param beanClass JavaBean的Class
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> beanClass) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        T bean = null;
        try {
            bean = mGson.fromJson(json, beanClass);
        } catch (Exception e) {
            Log.i("GsonUtils", "解析json数据时出现异常\njson = " + json, e);
        }
        return bean;
    }

    public static <T> T toObject(String json, Type type) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        T ret = null;
        try {
            ret = mGson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return ret;

    }

    public static <T> T toObject(JSONObject json, Type type) {
        if (json == null) {
            return null;
        }

        T ret = null;
        try {

            ret = mGson.fromJson(json.toString(), type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return ret;

    }


    public static String toJson(Object obj) {
        return mGson.toJson(obj);
    }

    public static String simpleMapToJsonStr(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "null";
        }
        String jsonStr = "{";
        Set<?> keySet = map.keySet();
        for (Object key : keySet) {
            jsonStr += "\"" + key + "\":\"" + map.get(key) + "\",";
        }
        jsonStr = jsonStr.substring(1, jsonStr.length() - 2);
        jsonStr += "}";
        return jsonStr;

    }

    /**
     * Json 转成 Map<>
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> getMapForJson(String jsonStr) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        }

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSONObject 转成 Map<String, String>
     *
     * @param jsonObject
     * @return
     */
    public static Map<String, String> getMapForJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        try {
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            String value;
            Map<String, String> valueMap = new HashMap<String, String>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.optString(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json 转成 List<Map<>>
     *
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> getlistForJson(String jsonStr) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        }

        List<Map<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            JSONObject jsonObj;
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = (JSONObject) jsonArray.get(i);
                list.add(getMapForJson(jsonObj.toString()));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }


}
