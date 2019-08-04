package com.camel.c8.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static Class<?> kindClass;

    public static JSONObject getJson(String json) {
        JSONObject jsonObject = null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            jsonObject = jsonArray.getJSONObject(0);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * @param JsonString 一个json格式的字符串  
     * @Summary 获取一个json对象
     * @return 正常返回一个json对象 异常返回 null
     */
    public static synchronized JSONObject getJsonObject(String JsonString) {
        JSONObject jsonObject = null;
        try {
            JsonString = getJsonStrFromNetData(JsonString);
            JSONArray entries = new JSONArray(JsonString);
            if (entries.length() > 0) {
                jsonObject = entries.getJSONObject(0);
            }
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param JsonString  json格式的字符串
     * @Summary 获取json 对象的数组
     * @return  返回Json 对象的数组
     */
    public static synchronized List<JSONObject> getJsonObjects(String JsonString) {
        JsonString = getJsonStrFromNetData(JsonString);
        ArrayList<JSONObject> array = new ArrayList<JSONObject>();
        try {
            JSONArray entries = new JSONArray(JsonString);
            for (int i = 0; i < entries.length(); i++) {
                JSONObject jsObject = entries.getJSONObject(i);
                if (jsObject != null) {
                    array.add(jsObject);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * @param jsonString 包含Json字符串的数据
     * @return json字符串
     * @summary 去除非Json的字符串部分
     */
    public static synchronized String getJsonStrFromNetData(String jsonString) {
        int first = jsonString.indexOf("[");
        int last = jsonString.lastIndexOf("]");
        String result = "";
        if (last > first) {
            result = jsonString.substring(first, last + 1);
        }
        return result;
    }


    /***
     * @summary 通过json字符串获取 实体对象
     * @param jsonStr Json 字符串
     * @return 实体数组
     */
    public static synchronized <T> ArrayList<T> getEntityFromJson(String jsonStr, Class<T> classOfT) {
        try {
            jsonStr = getJsonStrFromNetData(jsonStr);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<JsonObject>>() {
            }.getType();

            ArrayList<JsonObject> jsObjs = gson.fromJson(jsonStr, type);
            ArrayList<T> listOfT = new ArrayList<T>();
            for (JsonObject obj : jsObjs) {
                listOfT.add(new Gson().fromJson(obj, classOfT));
            }
            return listOfT;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Object To json String
     *
     * @param obj
     * @return json String
     */
    public static String objToJsonString(Object obj) {

        // 初始化返回值
        String json = "str_empty";

        if (obj == null) {
            return json;
        }

        StringBuilder buff = new StringBuilder();
        Field[] fields = obj.getClass().getFields();
        try {
            buff.append("[");
            buff.append("{");
            int i = 0;
            for (Field field : fields) {
                if (i != 0) {
                    buff.append(",");
                }
                buff.append(field.getName());
                buff.append(":");
                buff.append("\"");
                buff.append(field.get(obj) == null ? "" : field.get(obj));
                buff.append("\"");
                i++;
            }
            buff.append("}");
            buff.append("]");
            json = buff.toString();
        } catch (Exception e) {
            throw new RuntimeException("cause:" + e.toString());
        }
        return json;
    }

    public static String listToString(List ss) {
        StringBuffer s = new StringBuffer("");
        if (null != ss) {
            String[] str = new String[ss.size()];
            for (int i = 0; i < ss.size(); i++) {
                str[i] = ss.get(i).toString();
            }
            arrayToString(str);
            s.append(arrayToString(str));
        }
        return s.toString();
    }

    /**
     * 把数组转换成'',格式的字符串输出
     *
     * @param ss
     * @return
     */
    public static String arrayToString(String[] ss) {
        StringBuffer s = new StringBuffer("");
        if (null != ss) {
            for (int i = 0; i < ss.length - 1; i++) {
                s.append("'")
                        .append(ss[i])
                        .append("'")
                        .append(",");
            }
            if (ss.length > 0) {
                s.append("'").append(ss[ss.length - 1]).append("'");
            }
        }
        return s.toString();
    }

    /**
     * Convert an array of strings to one string.
     * Put the 'separator' string between each element.
     *
     * @param a
     * @param separator
     * @return
     */
    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if (a == null) {
            return "";
        }
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }
}