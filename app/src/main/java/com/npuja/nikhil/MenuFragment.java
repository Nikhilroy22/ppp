package com.npuja.nikhil;



import android.widget.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


import org.json.JSONException;
import org.json.JSONObject;

public class MenuFragment extends Fragment {
  
  Button logout;
  
    
  
  @Override
		public View onCreateView( LayoutInflater _inflater,  ViewGroup _container,  Bundle _savedInstanceState) {
		  
		  View _view = _inflater.inflate(R.layout.menufragment, _container, false);
		  
		  logout = _view.findViewById(R.id.logout);
		  
		  
		  logout.setOnClickListener(v ->{
		    
		   
		   CustomAlert.showalert(requireContext());
		   
		    //Toast.makeText(requireContext(), "Log Out", Toast.LENGTH_SHORT).show();
		    
		    
		  });
		  
	/*	  
		  
		  
		  

        viewModel.loadData(requireActivity()); // শুধু প্রথমবার call হবে, কারণ ভিতরে check আছে
		  */
		  return _view;
		  
		}}