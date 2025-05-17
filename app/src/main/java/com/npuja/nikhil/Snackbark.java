package com.npuja.nikhil;


import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Snackbark {

    // সাধারণ Snackbar দেখানোর মেথড
    public static void show(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    // ম্যানুয়ালি dismiss সহ Snackbar
    public static void showWithDismiss(View view, String message, int durationMillis) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();

        new Handler().postDelayed(snackbar::dismiss, durationMillis);
    }

    // অ্যাকশন সহ Snackbar (যেমন: রিফ্রেশ)
    public static void showWithAction(View view, String message, String actionText, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(actionText, listener);
        snackbar.show();
    }
}



//Snackbark.show(findViewById(android.R.id.content), lusername);