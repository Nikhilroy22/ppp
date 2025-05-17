package com.npuja.nikhil;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class http {

    private final OkHttpClient client;
    private final Handler mainHandler;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final Activity activity;

    public http(Activity activity) {
        this.client = new OkHttpClient();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.activity = activity;
    }

    public interface ResponseCallback {
        void onSuccess(String response);
        void onError(Exception e);
    }
    
    
     //Auth GET method
    public void getauth(String url, String token, ResponseCallback callback) {
        Request request = new Request.Builder().addHeader("Authorization", "Bearer " + token).url(url).build();
        makeRequest(request, callback);
    }

    // GET method
    public void get(String url, ResponseCallback callback) {
        Request request = new Request.Builder().url(url).build();
        makeRequest(request, callback);
    }

    // POST method
    public void post(String url, String json, ResponseCallback callback) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        makeRequest(request, callback);
    }

    // PUT method
    public void put(String url, String json, ResponseCallback callback) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        makeRequest(request, callback);
    }

    // Actual request executor with UI thread callback
    private void makeRequest(Request request, ResponseCallback callback) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mainHandler.post(() -> callback.onError(e)); // UI তে error callback
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();
                    mainHandler.post(() -> callback.onSuccess(res)); // UI তে success callback
                } else {
                  String res = response.body().string();
                    mainHandler.post(() -> callback.onSuccess(res));
                }
            }
        });
    }
}