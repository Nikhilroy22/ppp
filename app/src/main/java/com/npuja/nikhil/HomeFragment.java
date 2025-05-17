package com.npuja.nikhil;


import android.content.Intent;
import android.widget.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
	
	private MyViewModel viewModel;
	
	LinearLayout ideposit;
	
	Button button;
	TextView name, amount;
	private SwipeRefreshLayout swipeRefreshLayout;
	String tokenget;
	
	static boolean isFirstLoad = true; // ✅ প্রথমবার ট্র্যাক করার জন্
	
	
	@Override
		public View onCreateView( LayoutInflater _inflater,  ViewGroup _container,  Bundle _savedInstanceState) {
		  
		  View _view = _inflater.inflate(R.layout.homefragment, _container, false);
		  
		  swipeRefreshLayout = _view.findViewById(R.id.swipeRefresh);
		  
		  name = _view.findViewById(R.id.name);
		  amount = _view.findViewById(R.id.bdt);
		 
		 
		  ideposit = _view.findViewById(R.id.deposit);
		  
		  
		  //Deposite
		  ideposit.setOnClickListener(v -> {
		    
		    startActivity(new Intent(requireContext(), Profile.class));
		    
		  });
		  
		  
		  
		  StoreHelper gtoken = new StoreHelper(requireContext());
		  tokenget = gtoken.getData("token");
		  
		  if (isFirstLoad) {
		  progressdilog.show(requireContext(), "plz....");
		  }
		  
		 viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
		  
		  viewModel.getData().observe(getViewLifecycleOwner(), jsonObject -> {
            try {
                String namee = jsonObject.getString("name");
                String amounte = jsonObject.getString("amount");
                //String email = jsonObject.getString("email");

                name.setText(namee);
                amount.setText(amounte+" BDT");
                

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
		  
		  
		  if(!viewModel.hasData()){
		  authdata();
		  }
		  
		  swipeRefreshLayout.setOnRefreshListener(this::authdata);
		  
		  
		  //intent
		  
		  
		  
		  
		  
		  
		  return _view;
		  
		}
  
  
  
  public void authdata(){
    
    
     http helper = new http(requireActivity());

helper.getauth("http://localhost:8000/api/user", tokenget, new http.ResponseCallback() {
    @Override
    public void onSuccess(String response) {
      
      swipeRefreshLayout.setRefreshing(false);
      
      try{
        if (isFirstLoad) {
        progressdilog.hide();
        isFirstLoad = false; // ✅ পরবর্তীতে আর dialog দেখাবে না
        }
       
        
        JSONObject json = new JSONObject(response);
      //String sname = json.getString("name");
      
      viewModel.setData(json);
      
      }catch(JSONException e){
        
        swipeRefreshLayout.setRefreshing(false);
        
        Toast.makeText(requireContext(), "Joson Error", Toast.LENGTH_SHORT).show();
        
      }
      
      
       
       
        
    }

    @Override
    public void onError(Exception e) {
      
      swipeRefreshLayout.setRefreshing(false);
      
        Toast.makeText(requireContext(), "Post Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
     
    
    
  }
  
  
}