package com.npuja.nikhil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;





public class Profile extends AppCompatActivity {
  
  
  ImageView imageView;
    
  
  @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        
        
        
        
        imageView = findViewById(R.id.back);
        
        
        
        imageView.setOnClickListener(v ->{
          
          getOnBackPressedDispatcher().onBackPressed();
          
        });
        
   }
   }