package com.npuja.nikhil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.json.JSONException;
import org.json.JSONObject;
import androidx.lifecycle.ViewModelProvider;




public class Profile extends AppCompatActivity {
  
  TextView perror, aerror, serror;
  EditText phone, amount;
  ImageView imageView;
  Button deposit;
  Spinner mySpinner;
  private MyViewModel viewModel;
    
  
  @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        imageView = findViewById(R.id.back);
        
        
        
       mySpinner = findViewById(R.id.mySpinner);
        deposit = findViewById(R.id.depositBtn);
        phone = findViewById(R.id.phoneInput);
        amount = findViewById(R.id.amountInput);
        // validation
        perror = findViewById(R.id.phoneError);
        aerror = findViewById(R.id.amountError);
        serror = findViewById(R.id.spinnerError);
        
        
  
  
     // Back Button
        imageView.setOnClickListener(v ->{
          
          getOnBackPressedDispatcher().onBackPressed();
          
        });
       
       
        // Deposit Button setOnClickListener
        deposit.setOnClickListener(v ->{
          String sphone = phone.getText().toString();
          String samount = amount.getText().toString();
          String sspinner = mySpinner.getSelectedItem().toString();
          
          Boolean haserror = false;
          
          if(sphone.isEmpty()){
            
            phone.requestFocus();
            perror.setText("Phone Number Empty");
            perror.setVisibility(View.VISIBLE);
            haserror = true;
        
          }else if(sphone.length() < 3){
            phone.requestFocus();
            perror.setText("3 tar kom number");
            perror.setVisibility(View.VISIBLE);
            haserror = true;
          }else if(!sphone.matches("(01)[0-9]{9}$")){
              phone.requestFocus();
            perror.setText("Phone Number Not valid.");
            perror.setVisibility(View.VISIBLE);
            haserror = true;
          }else {
        // যেহেতু ইউজার ইনপুট দিয়েছে, তাই error লেখা লুকিয়ে দিন
        perror.setVisibility(View.GONE);
          
        }
          if(samount.isEmpty()){
            
            amount.requestFocus();
            aerror.setText("Amount Empty.");
            aerror.setVisibility(View.VISIBLE);
            haserror = true;
          }else{
            
            aerror.setVisibility(View.GONE);
          }
          
          
          if(sspinner.equals("Select Payment Method")){
            
            mySpinner.requestFocus();
            serror.setText("Select Payment Method.");
            serror.setVisibility(View.VISIBLE);
            haserror = true;
            
          }else{
            
            serror.setVisibility(View.GONE);
          }
          
          if(!haserror){
          Toast.makeText(this, "Click korci", Toast.LENGTH_SHORT).show();
          
          }
          
          
        });
        
        
        
       //Spinner
      
      
      

// Spinner এর জন্য ডেটা
String[] options = {"Select Payment Method", "Bkash", "Nagad", "Upay"};
ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
mySpinner.setAdapter(adapter);
      
      
      
      viewModel = new ViewModelProvider(Profile.this).get(MyViewModel.class);
		  
		  viewModel.getData().observe(Profile.this, jsonObject -> {
            try {
                String namee = jsonObject.getString("name");
                String amounte = jsonObject.getString("amount");
                //String email = jsonObject.getString("email");

                perror.setText(namee);
            perror.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Click korci ok", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Click korci faided", Toast.LENGTH_SHORT).show();
            }
        }); 
  
        
        
   }
   }