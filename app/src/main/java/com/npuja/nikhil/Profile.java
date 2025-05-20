package com.npuja.nikhil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.*;

import org.json.JSONException;
import org.json.JSONObject;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
  
  FirebaseFirestore db;
  
  TextView perror, aerror, serror, nagad;
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
        nagad = findViewById(R.id.nagadset);
        
        
  
  
     // Back Button
        imageView.setOnClickListener(v ->{
          
          getOnBackPressedDispatcher().onBackPressed();
          
        });
       // click to copy
       nagad.setOnClickListener(v ->{
          
          String textToCopy = nagad.getText().toString();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(Profile.this, "টেক্সট কপি হয়েছে", Toast.LENGTH_SHORT).show();
          
        });
       
       
       
       
        // Deposit Button 
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
            db = FirebaseFirestore.getInstance();
            
            
          Map<String, Object> post = new HashMap<>();
post.put("mobile", sphone);
post.put("amount", samount);
post.put("method", sspinner);


db.collection("deposit")
    .add(post)
    .addOnSuccessListener(documentReference -> 
        Toast.makeText(this, "পোস্ট সফলভাবে যুক্ত হয়েছে", Toast.LENGTH_SHORT).show())
    .addOnFailureListener(e -> 
        Toast.makeText(this, "পোস্ট ব্যর্থ: " + e.getMessage(), Toast.LENGTH_SHORT).show());
          
          }
          
          
        });
        
        
   
      
      

// Spinner এর জন্য ডেটা
String[] options = {"Select Payment Method", "Bkash", "Nagad", "Upay"};
ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
mySpinner.setAdapter(adapter);
      
      
        
   }
   
   
   

   }