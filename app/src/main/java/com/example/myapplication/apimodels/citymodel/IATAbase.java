package com.example.myapplication.apimodels.citymodel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface IATAbase {

    @GET("/data/ru/airports.json")
    Call<List<Airport>> getAllAirports();

    @GET("/data/ru/cities.json")
    Call<List<City>> getAllCities();
}
