package com.npuja.nikhil;
import android.app.Activity;

// ViewModel & LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;


// JSON
import org.json.JSONObject;
import org.json.JSONException;

public class MyViewModel extends ViewModel {
    private final MutableLiveData<JSONObject> data = new MutableLiveData<>();

    public LiveData<JSONObject> getData() {
        return data;
    }

    public void setData(JSONObject newData) {
        data.postValue(newData); // background thread থেকে update
    }

    public boolean hasData() {
        return data.getValue() != null;
    }
}