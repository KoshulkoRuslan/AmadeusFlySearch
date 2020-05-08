package com.example.myapplication.apimodels.citymodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CityDao {
    @Insert
    void insertAll(List<City> cities);
    @Query("SELECT * from City")
    LiveData<List<City>> getAllCities();
}
