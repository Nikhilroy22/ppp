package com.npuja.nikhil;

import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.Thread;



public class SplashActivity extends AppCompatActivity {
  
  String tokenget;
  
  ProgressBar progressBar;
    
    //Handler handler = new Handler();
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        
      //  progressBar = findViewById(R.id.progressbar1);
        
        
        
        
        StoreHelper gtoken = new StoreHelper(SplashActivity.this);
		   tokenget = gtoken.getData("token");
     
     
     
     
        if(tokenget != null){
        
       verifytoken();
        
        }else{
        
        
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
          
          startActivity(new Intent(this, LoginActivity.class));
            finish();
          
          
        }, 2500);
        }
        
        
        
   }
  
  
  
  public void verifytoken(){
    
    http helper = new http(SplashActivity.this);
    
    helper.getauth("http://localhost:8000/api/user", tokenget, new http.ResponseCallback() {
    @Override
    public void onSuccess(String response) {
   
   try{
   JSONObject json = new JSONObject(response);
    Toast.makeText(SplashActivity.this, "Token Ace ", Toast.LENGTH_SHORT).show();
    
    startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
    
    
   } catch(JSONException e){
     Toast.makeText(SplashActivity.this, "TOKEN " + e.getMessage(), Toast.LENGTH_SHORT).show();
     
     startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
     
   }
    }

    @Override
    public void onError(Exception e) {
    Toast.makeText(SplashActivity.this, "Token Nei " + e.getMessage(), Toast.LENGTH_SHORT).show();
    
    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
    
    }
});
     
    
  }
  
}