package com.npuja.nikhil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.*;

public class OkHttpHelper {

    private final OkHttpClient client = new OkHttpClient();
    private final Context context;

    public OkHttpHelper(Context context) {
        this.context = context;
    }

    public void login(String email, String password, String url) {
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast("ইন্টারনেট সমস্যা: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();

                    try {
                        JSONObject json = new JSONObject(responseData);
                        String token = json.getString("token");
                        showToast("লগইন সফল! টোকেন: " + token);
                    } catch (JSONException e) {
                        showToast("JSON পার্সিং ত্রুটি");
                    }
                } else {
                    showToast("লগইন ব্যর্থ! সঠিক তথ্য দিন।");
                }
            }
        });
    }

    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() ->
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        );
    }
}