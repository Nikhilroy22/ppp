package com.npuja.nikhil;



import android.widget.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;
import java.util.ArrayList;

public class DataFragment extends Fragment {
  
  private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<DataModel> dataList;
  
  
  @Override
		public View onCreateView( LayoutInflater _inflater,  ViewGroup _container,  Bundle _savedInstanceState) {
		  
		  View _view = _inflater.inflate(R.layout.datafragment, _container, false);
		  
		  recyclerView = _view.findViewById(R.id.recyclerView);
		  
		  recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
		  
		  
		  dataList = new ArrayList<>();
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);
		  
		  fetchdata();
		  
		  return _view;
		  
		}
  
  public void fetchdata(){
    progressdilog.show(requireContext(), "plz....");
    http helper = new http(requireActivity());

helper.get("http://localhost:8000/api/data", new http.ResponseCallback() {
    @Override
    public void onSuccess(String response) {
      
     // Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show();
    progressdilog.hide();
      try{
        JSONArray jsonArray = new JSONArray(response);
        
        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                          

                            DataModel data = new DataModel(name);
                            dataList.add(data);
                        }
        
        adapter.notifyDataSetChanged();
        
        
        
      }catch(JSONException e){
        
        Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    
        
      }
      
      
    }

    @Override
    public void onError(Exception e) {
      
      
      
        Toast.makeText(requireContext(), "Post Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
    
    
    
  }
  
}