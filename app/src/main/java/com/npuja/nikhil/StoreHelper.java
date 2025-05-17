package com.npuja.nikhil;

import android.content.Context;
import android.content.SharedPreferences;

public class StoreHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // কনস্ট্রাকটর
    public StoreHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // ডেটা সঞ্চয় করার জন্য একটি মেথড
    public void saveData(String key, String value) {
        editor.putString(key, value);
        editor.apply();  // apply() ব্যবহার করলে অটো-সেভ হয় ব্যাকগ্রাউন্ড থ্রেডে
    }

    // ডেটা রিড করার জন্য একটি মেথড
    public String getData(String key) {
        return sharedPreferences.getString(key, null);  // ডিফল্ট মান: ""
    }

    // ডেটা মুছে ফেলার জন্য একটি মেথড
    public void clearData(String key) {
        editor.remove(key);
        editor.apply();
    }

    // সমস্ত ডেটা মুছে ফেলার জন্য একটি মেথড
    public void clearAllData() {
        editor.clear();
        editor.apply();
    }
}