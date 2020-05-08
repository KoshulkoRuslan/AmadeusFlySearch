package com.example.myapplication.apimodels.flyOffer;

public class LocationFields {
    String cityCode;
    String countryCode;

    public LocationFields(String cityCode, String countryCode) {
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
