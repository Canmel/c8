package com.camel.c8.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.camel.c8.activity.LoginActivity;
import com.camel.c8.exception.UnAuthenticationException;
import com.camel.c8.service.HttpCallBack;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    public static OkHttpUtils okHttpUtils = new OkHttpUtils();

    public OkHttpClient okHttpClient;

    public Handler handler;

    public String url;

    public Context context;

    public Map<String, Object> params;

    public Response response;

    public HttpCallBack httpCallBack;

    public OkHttpUtils() {
    }

    public static OkHttpUtils getInstance() {
        return okHttpUtils;
    }

    public Response get(Context con, String urls, Map<String, Object> param, HttpCallBack callBack) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        init(urls, param, con, callBack);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        url = url + "?access_token=" + SessionUtils.accessToken(context) + getUrlParams(params);
                        Request request = new Request.Builder().url(url)
                                .addHeader("Authorization", "Basic YW5kcm9pZDphbmRyb2lk")
                                .get().build();
                        Call call = okHttpClient.newCall(request);
                        Integer code = null;
                        JSONObject jsonObject = null;
                        JSONObject jsonData = null;
                        String msg = "";
                        JSONArray jsonArray = new JSONArray();
                        Message message = new Message();
                        try {
                            response = call.execute();
                            if (response.isSuccessful()) {
                                String body = response.body().string();
                                jsonObject = JsonHelper.getJson("[" + body + "]");
                                code = (Integer) jsonObject.get("code");
                                jsonData = (JSONObject) jsonObject.get("data");
                                jsonArray = jsonData.getJSONArray("list");
                                msg = (String) jsonObject.get("msg");
                                Map<String, Object> m = new HashMap<>();
                                m.put("code", code);
                                m.put("msg", msg);
                                m.put("data", jsonData);
                                m.put("list", jsonArray);
                                message.what = 123;
                                message.obj = m;
                            } else {
                                Log.d("OKHTTPCLIENT", "请求失败");
                                if (isUnAuthorized(response)) {
                                    throw new UnAuthenticationException();
                                }
                            }



                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("OKHTTPCLIENT", "GET请求失败");
                        } catch (JSONException e) {
                            Log.d("ONHTTPCLIENT", "JSON取值或转换错误");
                        } catch (UnAuthenticationException e) {
                            Log.d("ONHTTPCLIENT", e.getMessage());
                            Intent i = new Intent(context, LoginActivity.class);
                            context.startActivity(i);
                        }
                        handler.sendMessage(message);


                    }
                }
        ).start();

        return response;
    }

    public static String getUrlParams(Map<String, Object> params) {
        String p = "";
        for (String key : params.keySet()) {
            Object value = params.get(key);
            p += "&" + key + "=" + value.toString();
        }
        if (p.length() > 1) {
            p = p.substring(1, p.length() - 1);
        }
        return p;
    }

    void init(String url, Map<String, Object> params, Context context, HttpCallBack callBack) {
        this.url = url;
        this.params = params;
        this.context = context;
        this.httpCallBack = callBack;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                callBack.call(msg);
            }
        };
    }

    public static boolean isUnAuthorized(Response response) {
        return response.code() == 401;
    }
}
