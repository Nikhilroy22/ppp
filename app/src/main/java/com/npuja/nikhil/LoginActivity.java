package com.npuja.nikhil;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.*;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {
  
  EditText username, password;
  TextView usernameError, usernameError2;
  Button singup, login;
  
  
  
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
//Find View Id


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        
        
        singup = findViewById(R.id.singup);
        login = findViewById(R.id.login);
        
        
        usernameError = findViewById(R.id.usernameError);
        usernameError2 = findViewById(R.id.usernameError2);
        
      //  fcm token
    
    
    FirebaseMessaging.getInstance().getToken()
    .addOnCompleteListener(task -> {
        if (!task.isSuccessful()) {
            username.setText("Fetching FCM registration token failed");
            return;
        }

        // Get new FCM registration token
        String token = task.getResult();

        // Firestore এ সেভ করো
        username.setText(token);
        
    });
        
       //Switch
       
       Switch mySwitch = findViewById(R.id.mySwitch);
       
       
       Boolean act = true;
       
       mySwitch.setChecked(act);

mySwitch.setOnCheckedChangeListener(( buttonView, isChecked) -> {
        if (isChecked) {
            // Active অবস্থা
            Toast.makeText(getApplicationContext(), "অ্যাকটিভ", Toast.LENGTH_SHORT).show();
        } else {
            // Inactive অবস্থা
            Toast.makeText(getApplicationContext(), "ইনঅ্যাকটিভ", Toast.LENGTH_SHORT).show();
        }
    
});
    
    
      
        
//Register account click
        singup.setOnClickListener(v -> {
          
          startActivity(new Intent(this, RegisterActivity.class));
          
          
        });
        
        
 //login Account
     
     login.setOnClickListener(v -> {
       
       
       String lusername = username.getText().toString();
          String lpassword = password.getText().toString();
          
          Boolean haserror = false;
          
          if (lusername.isEmpty()) {
            //username.setError("ইউজারনেম দিন");
            
            username.requestFocus();
            usernameError.setText("ইউজারনেম দিন");
    usernameError.setVisibility(View.VISIBLE);
            
            haserror = true;
        }
          else {
        // যেহেতু ইউজার ইনপুট দিয়েছে, তাই error লেখা লুকিয়ে দিন
        usernameError.setVisibility(View.GONE);
          
        }
        
        
        if(lpassword.isEmpty()){
          
          password.requestFocus();
          usernameError2.setText("Password দিন");
    usernameError2.setVisibility(View.VISIBLE);
    haserror = true;
        }else{
          
          usernameError2.setVisibility(View.GONE);
          
        }
       
       if(!haserror){
         
         
       
       progressdilog.show(this, "plz....");
       
       String jsonBody = "{\"username\":\"" + lusername + "\",\"password\":\"" + lpassword + "\"}";
          
          http helper = new http(this);

helper.post("http://localhost:8000/api/login", jsonBody, new http.ResponseCallback() {
    @Override
    public void onSuccess(String response) {
      
      
      try{
        JSONObject json = new JSONObject(response);
        
        if(json.has("token")){
          
          startActivity(new Intent(LoginActivity.this, MainActivity.class));
          finish();
          
        String token = json.getString("token");
        
        StoreHelper savedtoken = new StoreHelper(LoginActivity.this);
        
        savedtoken.saveData("token", token);
        
        
          Snackbark.show(findViewById(android.R.id.content), token);
          
        }
        progressdilog.hide();
        String msg = json.getString("message");
        Snackbark.show(findViewById(android.R.id.content), msg);
        
        
      }catch(JSONException e){
        
        progressdilog.hide();
        
        Snackbark.show(findViewById(android.R.id.content), e.getMessage());
        
      }
      
      
      
    
      
      
      
      
      
      
      
    }
    @Override
    public void onError(Exception e) {
      
      progressdilog.hide();
      
        Toast.makeText(LoginActivity.this, "Post Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
});

}

   
        });
        
   }}