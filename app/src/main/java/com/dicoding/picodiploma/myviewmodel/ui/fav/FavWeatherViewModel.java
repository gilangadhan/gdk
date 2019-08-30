package com.dicoding.picodiploma.myviewmodel.ui.fav;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.dicoding.picodiploma.myviewmodel.data.database.DatabaseQuery;
import com.dicoding.picodiploma.myviewmodel.data.model.WeatherItems;

import java.util.List;

public class FavWeatherViewModel extends AndroidViewModel {

    private DatabaseQuery databaseQuery;

    public FavWeatherViewModel(@NonNull Application application) {
        super(application);
        databaseQuery = DatabaseQuery.getInstance(application.getApplicationContext());
    }

    LiveData<List<WeatherItems>> getWeathers() {
        databaseQuery.open();
        MutableLiveData<List<WeatherItems>> list = new MutableLiveData<>();

        list.postValue(databaseQuery.getAllNotes());
        databaseQuery.close();
        return list;
    }
}
