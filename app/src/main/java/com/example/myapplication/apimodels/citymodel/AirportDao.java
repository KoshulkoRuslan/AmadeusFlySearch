package com.example.myapplication.apimodels.citymodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AirportDao {
    @Insert
    void insertAll(List<Airport> airports);

    @Query("SELECT c.name AS 'cityName' , a.name AS 'airportName', a.code AS 'airportCode', c.country_code AS 'countryCode' \n" +
            "FROM city c INNER JOIN airport a ON c.code = a.city_code \n" +
            "WHERE c.name IS NOT NULL AND (a.name IS NOT NULL AND \n" +
            "(a.name NOT LIKE '%вокзал%' AND\n" +
            "(a.flightable = '1' AND ((a.name LIKE :searchPath)  OR  (a.code LIKE :searchPath) OR (c.name  LIKE :searchPath) OR (c.code  LIKE :searchPath) ))))\n" +
            "UNION\n" +
            "SELECT c.name, c.name ='Любой аэропорт', c.code,c.country_code\n" +
            "FROM city c\n" +
            "WHERE  name LIKE :searchPath  OR  code LIKE :searchPath ")
    List<AutocompleteAirportSearch> getAutocompleteAirport(String searchPath);
}
