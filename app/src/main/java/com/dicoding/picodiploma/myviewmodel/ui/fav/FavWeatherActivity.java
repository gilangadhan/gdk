package com.dicoding.picodiploma.myviewmodel.ui.fav;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.dicoding.picodiploma.myviewmodel.R;
import com.dicoding.picodiploma.myviewmodel.data.model.WeatherItems;

import java.util.List;

public class FavWeatherActivity extends AppCompatActivity {
    private FavWeatherAdapter adapter;
    private EditText edtCity;
    private ProgressBar progressBar;
    private FavWeatherViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_weather);
        viewModel = ViewModelProviders.of(this).get(FavWeatherViewModel.class);

        adapter = new FavWeatherAdapter();
        adapter.notifyDataSetChanged();

        viewModel.getWeathers().observe(this, new Observer<List<WeatherItems>>() {
            @Override
            public void onChanged(@Nullable List<WeatherItems> weatherItems) {
                adapter.setData(weatherItems);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
