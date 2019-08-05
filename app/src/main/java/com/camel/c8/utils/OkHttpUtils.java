package com.camel.c8.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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

    public OkHttpUtils() {
    }

    public static OkHttpUtils getInstance() {
        return okHttpUtils;
    }

    public Response get(Context con, String urls, Map<String, Object> param) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        init(urls, param, con);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        url = url + "?access_token=" + SessionUtils.accessToken(context) + getUrlParams(params);
                        Request request = new Request.Builder().url(url)
                                .addHeader("Authorization", "Basic YW5kcm9pZDphbmRyb2lk")
                                .get().build();
                        Call call = okHttpClient.newCall(request);
                        JSONObject jsonObject = null;
                        try {
                            response = call.execute();
                            String body = response.body().string();
                            jsonObject = JsonHelper.getJson("[" + body + "]");
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("OKHTTPCLIENT", "GET请求失败");
                        }




                        Message message = new Message();
                        message.what = 123;
                        message.obj = jsonObject;
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

    void init(String url, Map<String, Object> params, Context context) {
        this.url = url;
        this.params = params;
        this.context = context;
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 123:
                        System.out.println("--------------------");
                        break;
                }
            }
        };
    }
}
