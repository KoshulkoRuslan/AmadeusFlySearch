package com.example.myapplication.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.myapplication.apimodels.citymodel.AutocompleteAirportSearch;
import com.example.myapplication.databases.AppCodeIataDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StartSearchRepository {
    private static AppCodeIataDatabase db;
    private Context context;

    public StartSearchRepository(Context context) {
        this.context = context;
        db = AppCodeIataDatabase.getAutocompleteDatabase(context);

    }

    public List<AutocompleteAirportSearch> getAutocompleteAirports(String string){
        List<AutocompleteAirportSearch> resultList = new ArrayList<>();
        try {
            resultList = new AsyncAutocompleteResult().execute("%"+ string + "%").get();
            return resultList;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static class AsyncAutocompleteResult extends AsyncTask<String,Void, List<AutocompleteAirportSearch>> {

        @Override
        protected List<AutocompleteAirportSearch> doInBackground(String... strings) {
            Log.d("TAG", "Запрашиваем рузультаты по автокомплиту для: " + strings[0]);
            return db.airportDao().getAutocompleteAirport(strings[0]);
        }
    }
}
