package com.camel.c8.utils;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SessionUtils {

    public static final String ACCESS_TOKEN_KEY = "access_token";

    public static final String REFRESH_TOKEN_KEY = "refresh_token";

    public static String accessToken(Context context) {
        return getCache(context).getAsString(ACCESS_TOKEN_KEY);
    }

    public static void accessToken(Context context, String accessToken) {
        getCache(context).put(ACCESS_TOKEN_KEY, accessToken);
    }

    public static void refreshToken(Context context, String accessToken) {
        getCache(context).put(REFRESH_TOKEN_KEY, accessToken);
    }

    public static void delete(Context context) {
        getCache(context).remove(ACCESS_TOKEN_KEY);
        getCache(context).remove(REFRESH_TOKEN_KEY);
    }


    public static ACache getCache(Context context) {
        return ACache.get(context);
    }

    public static void logout() {
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            FormBody formBody = new FormBody.Builder().build();
            String url = "http://192.168.2.225:8080/auth/session/exit";
            Request request = new Request.Builder().url(url)
                    .addHeader("Authorization", "Basic YW5kcm9pZDphbmRyb2lk")
                    .delete(formBody).build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            String body = response.body().string();

            JSONObject jsonObject = JsonHelper.getJson("[" + body + "]");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
