package com.dicoding.picodiploma.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static com.dicoding.picodiploma.mylistview.MainActivity.KEY_HERO;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Hero hero = getIntent().getParcelableExtra(KEY_HERO);
        String desc = String.format("%s \n %s", hero.getName(), hero.getDescription());
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}
