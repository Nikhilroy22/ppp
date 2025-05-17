package com.npuja.nikhil;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;


public class CustomAlert {
  
  
  public static void showalert(Context context) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.custom_alert, null);
    builder.setView(view);
    builder.setCancelable(false);

    AlertDialog dialog = builder.create();

    Button okButton = view.findViewById(R.id.okButton);
    Button cancelButton = view.findViewById(R.id.cancelButton);

    okButton.setOnClickListener(v -> {
        StoreHelper sss = new StoreHelper(context);
        sss.clearData("token");
        
        context.startActivity(new Intent(context, LoginActivity.class));
            if (context instanceof Activity) {
    ((Activity) context).finish();
}
        
    });

    cancelButton.setOnClickListener(v -> {
        Toast.makeText(context, "আপনি না বলেছেন", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    });

    dialog.show();
}
  
}
