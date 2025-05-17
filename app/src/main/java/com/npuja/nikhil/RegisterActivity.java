package com.npuja.nikhil;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.*;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
  EditText name, username, password;
  Button register;
  
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        
        // find View find
        
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.singupbtn);
        
        //Register Click
        
        register.setOnClickListener(v ->{
          
          String rname = name.getText().toString();
          String rusername = username.getText().toString();
          String rpassword = password.getText().toString();
         
        if (rname.isEmpty()) {
            name.setError("ইউজারনেম দিন");
            
            name.requestFocus();
            return;
        }else if (rusername.isEmpty()) {
            username.setError("ইউজারনেম দিন");
            
            username.requestFocus();
            return;
        }else if(rpassword.isEmpty()){
          password.setError("ইউজারনেম দিন");
          password.requestFocus();
          return;
        }
         
         
         
          progressdilog.show(this, "plz....");
          // Http
          http helper = new http(this);
          
       String jsonBody = "{\"name\":\"" + rname + "\",\"username\":\"" + rusername + "\",\"password\":\"" + rpassword + "\"}";
       
helper.post("http://localhost:8000/api/register", jsonBody, new http.ResponseCallback() {
    @Override
    public void onSuccess(String response) {
      progressdilog.hide();
      try{
        JSONObject json = new JSONObject(response);
        
        Boolean succ = json.getBoolean("success");
        if (succ){
        
        Alert.show(RegisterActivity.this, "Alert", "Register Successfully");
        
         //Toast.makeText(RegisterActivity.this, "poss success", Toast.LENGTH_SHORT).show();
        }else{
          
          String ress = json.getString("error");
          Alert.show(RegisterActivity.this, "Alert", ress);
          
          //Toast.makeText(RegisterActivity.this, ress, Toast.LENGTH_SHORT).show();
          
        }
        
        
        
      }catch(JSONException e){
         Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
      }
      
      
      
       
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(RegisterActivity.this, "Post Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
});


          
        });
        
        
        
   }}