package com.example.recyclingapp;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

    public class OkHttpHandler {

        public OkHttpHandler() {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        public void logHistory(String url) throws Exception {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("",
                    MediaType.parse("text/plain"));
            Request request = new Request.Builder().url(url).method("POST",
                    body).build();
            Response response = client.newCall(request).execute();
            System.out.println("My Response: " + response.body().string());
        }

        ArrayList<String[]> fetching(String url) throws Exception {
            ArrayList<String[]> uList = new ArrayList<>();
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("",
                    MediaType.parse("text/plain"));
            Request request = new Request.Builder().url(url).method("POST",
                    body).build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            //System.out.println("My Response: " + data);
            try {

                JSONObject json = new JSONObject(data);
                Iterator<String> keys = json.keys();
                while (keys.hasNext()) {
                    String username = keys.next();
                    String password = json.get(username).toString();
                    String res[] = {username,password};
                    uList.add(res);
                }
            } catch(JSONException e)
            {
                e.printStackTrace();
            }
            return uList;
        }
    }
