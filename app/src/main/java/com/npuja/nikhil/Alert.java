package com.npuja.nikhil;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Alert {

    private static AlertDialog alertDialog;

    public static void showExitDialog(Activity activity) {
        if (alertDialog != null && alertDialog.isShowing()) {
            return; // আগের ডায়ালগ যদি চলমান থাকে, নতুনটি না দেখায়
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("সতর্কতা");
        builder.setMessage("আপনি কি অ্যাপটি বন্ধ করতে চান?");
        builder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                activity.finish(); // অ্যাপ বন্ধ
            }
        });
        builder.setNegativeButton("না", null);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public static void show(Activity activity, String title, String message) {
        if (alertDialog != null && alertDialog.isShowing()) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("ঠিক আছে", null);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public static void dismiss() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }
}