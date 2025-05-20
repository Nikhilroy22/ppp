package com.npuja.nikhil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class NotificationActivity extends AppCompatActivity {
  
  ImageView imageView;
  
  
  @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        
        imageView = findViewById(R.id.back);
        
        // Back Button
        imageView.setOnClickListener(v ->{
          
          getOnBackPressedDispatcher().onBackPressed();
          
        });
       
       
       try {
            int result = 10 / 0; // এখানে exception হবে
        } catch (Exception e) {
            e.printStackTrace(); // exception এর বিস্তারিত logcat-এ দেখাবে
            Toast.makeText(this, "ভুল হয়েছে!", Toast.LENGTH_SHORT).show();
        }
       
        
   }}