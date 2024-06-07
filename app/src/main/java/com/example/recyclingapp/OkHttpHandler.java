package com.example.recyclingapp;

import android.os.StrictMode;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

    public class OkHttpHandler {
        public OkHttpHandler() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        public void logHistory(String url) throws Exception {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("",MediaType.parse("text/plain"));
            Request request = new Request.Builder().url(url).method("POST",body).build();
            Response response = client.newCall(request).execute();
            System.out.println("My Response: " + response.body().string());
        }
        public void update(String url) throws Exception {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("",MediaType.parse("text/plain"));
            Request request = new Request.Builder().url(url).method("POST",body).build();
            Response response = client.newCall(request).execute();
            System.out.println("My Response: " + response.body().string());
        }
        private OkHttpClient client = new OkHttpClient();
            public ArrayList<User> fetching(String url) throws IOException, JSONException {
                ArrayList<User> uList = new ArrayList<>();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                String data = response.body().string();

                JSONObject json = new JSONObject(data);
                Iterator<String> keys = json.keys();
                while (keys.hasNext()) {
                    String ID = keys.next();
                    JSONObject userJson = json.getJSONObject(ID);

                    User user = new User(
                            Integer.parseInt(ID),
                            userJson.getString("Name"),
                            userJson.getString("Email"),
                            userJson.getString("Username"),
                            userJson.getString("Password"),
                            userJson.getInt("Points"),
                            (float) userJson.getDouble("Aluminium_kg"),
                            (float) userJson.getDouble("Glass_kg"),
                            (float) userJson.getDouble("Paper_kg"),
                            (float) userJson.getDouble("Plastic_kg")
                    );
                    uList.add(user);
                }
                return uList;
            }
    }
