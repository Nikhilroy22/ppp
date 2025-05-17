package com.npuja.nikhil;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.*;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;


import java.util.List;


public class MainActivity extends AppCompatActivity {
  
  
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
       // progressdilog.show(this, "plz wait....");
       
      if (savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, new HomeFragment()) // your default fragment
            .commit();
            
    }
       
       
       BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation1);
       
       
        bottomNavigationView.setOnItemSelectedListener(item -> {
        int id = item.getItemId();
        Fragment selectedFragment = null;
       
        if (id == R.id.nav_home) {
            // Handle home
            
            selectedFragment = new HomeFragment();
          
         // Toast.makeText(MainActivity.this, "home clicj", Toast.LENGTH_SHORT).show();
            
            
        } else if (id == R.id.nav_menu) {
            // Handle profile
          selectedFragment = new MenuFragment();
           
            
            
        } else if (id == R.id.nav_search) {
            // Handle settings
           // startActivity(new Intent(this, LoginActivity.class));
            selectedFragment = new DataFragment();
            
            
            
            
        }
        
        if (selectedFragment != null) {
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, selectedFragment)
                    .commit();
                    
        }
        return true;
    
});
       
    //Auth Datq
    
    
    
    
    
   
   } 

}