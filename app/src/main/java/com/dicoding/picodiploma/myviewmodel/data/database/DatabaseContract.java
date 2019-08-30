package com.dicoding.picodiploma.myviewmodel.data.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_WEATHER = "weather";
    static final class WeatherColumns implements BaseColumns {
        static String NAME = "name";
        static String WEATHER = "currentWeather";
        static String DESCRIPTION = "description";
        static String TEMPERATURE = "temperature";
    }
}