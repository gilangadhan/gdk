package com.dicoding.picodiploma.myviewmodel.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.picodiploma.myviewmodel.data.model.WeatherItems;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dicoding.picodiploma.myviewmodel.data.database.DatabaseContract.TABLE_WEATHER;
import static com.dicoding.picodiploma.myviewmodel.data.database.DatabaseContract.WeatherColumns.DESCRIPTION;
import static com.dicoding.picodiploma.myviewmodel.data.database.DatabaseContract.WeatherColumns.NAME;
import static com.dicoding.picodiploma.myviewmodel.data.database.DatabaseContract.WeatherColumns.TEMPERATURE;
import static com.dicoding.picodiploma.myviewmodel.data.database.DatabaseContract.WeatherColumns.WEATHER;

public class DatabaseQuery {
    private static DatabaseHelper dataBaseHelper;
    private static DatabaseQuery INSTANCE;
    private static SQLiteDatabase database;

    private DatabaseQuery(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static DatabaseQuery getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseQuery(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }
    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<WeatherItems> getAllNotes() {
        ArrayList<WeatherItems> arrayList = new ArrayList<>();
        Cursor cursor = database.query(TABLE_WEATHER, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        WeatherItems weatherItems;
        if (cursor.getCount() > 0) {
            do {
                weatherItems = new WeatherItems();
                weatherItems.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                weatherItems.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                weatherItems.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                weatherItems.setCurrentWeather(cursor.getString(cursor.getColumnIndexOrThrow(WEATHER)));
                weatherItems.setTemperature(cursor.getString(cursor.getColumnIndexOrThrow(TEMPERATURE)));
                arrayList.add(weatherItems);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertWeather(WeatherItems weatherItems) {
        ContentValues args = new ContentValues();
        args.put(NAME, weatherItems.getName());
        args.put(DESCRIPTION, weatherItems.getDescription());
        args.put(WEATHER, weatherItems.getCurrentWeather());
        args.put(TEMPERATURE, weatherItems.getTemperature());
        return database.insert(TABLE_WEATHER, null, args);
    }

    public int deleteWeather(int id) {
        return database.delete(TABLE_WEATHER, _ID + " = '" + id + "'", null);
    }
}
