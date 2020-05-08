package com.example.myapplication.apimodels.citymodel;

public class AutocompleteAirportSearch {
    String cityName;
    String airportName;
    String airportCode;
    String countryCode;

    public AutocompleteAirportSearch(String cityName, String airportName, String airportCode, String countryCode) {
        this.cityName = cityName;
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
