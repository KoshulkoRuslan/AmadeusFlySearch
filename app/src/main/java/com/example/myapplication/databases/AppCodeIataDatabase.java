package com.example.myapplication.databases;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.apimodels.citymodel.Airport;
import com.example.myapplication.apimodels.citymodel.AirportDao;
import com.example.myapplication.apimodels.citymodel.City;
import com.example.myapplication.apimodels.citymodel.CityDao;
import com.example.myapplication.apimodels.citymodel.IATAbase;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {Airport.class, City.class},version = 1,exportSchema = false)
public abstract class AppCodeIataDatabase extends RoomDatabase {
    public abstract AirportDao airportDao();
    public abstract CityDao cityDao();
    private static AppCodeIataDatabase instance;

    public static synchronized AppCodeIataDatabase getAutocompleteDatabase(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,AppCodeIataDatabase.class,"app_database")
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.travelpayouts.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IATAbase iatAbase = retrofit.create(IATAbase.class);
            try {
                new InitCity().execute(iatAbase.getAllCities().execute().body());
            } catch (IOException e) {
                e.printStackTrace();
            }


            iatAbase.getAllAirports().enqueue(new retrofit2.Callback<List<Airport>>() {
             @Override
             public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
                 if (response.isSuccessful() && response.body() != null)
                 {
                     Log.d("TAG", "onResponse: получаем данные с аэропортами");
                     new InitAirports().execute(response.body());
                 }
             }

             @Override
             public void onFailure(Call<List<Airport>> call, Throwable t) {

             }
         });
        }
    };


    public static class InitCity extends AsyncTask<List<City>,Void,Void> {


        @Override
        protected Void doInBackground(List<City>... lists) {
            Log.d("TAG", "onResponse: записываем таблицу с городами");
            instance.cityDao().insertAll(lists[0]);
            return null;
        }
    }

    public static class InitAirports extends AsyncTask<List<Airport>,Void,Void> {


        @Override
        protected Void doInBackground(List<Airport>... lists) {
            Log.d("TAG", "onResponse: записываем таблицу с аэропортами");
            instance.airportDao().insertAll(lists[0]);
            return null;
        }
    }
}
