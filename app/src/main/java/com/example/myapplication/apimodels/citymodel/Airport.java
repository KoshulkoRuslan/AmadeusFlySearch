package com.example.myapplication.apimodels.citymodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.versionedparcelable.NonParcelField;

import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = @ForeignKey(entity = City.class,parentColumns = "code",childColumns = "city_code"))
public class Airport {
    @SerializedName("time_zone")
    @ColumnInfo(name = "time_zone")
    String timeZone;

    String name;

    @Embedded
    Coordinates coordinates;

    //Код аэропорта DME, SVO
    @NonNull
    @PrimaryKey(autoGenerate = false)
    String code;

    @Embedded
    NameTranslations name_translations;

    @SerializedName("country_code")
    @ColumnInfo(name = "country_code")
    String countryCode;

    Boolean flightable;

    //Код города MOW
    @SerializedName("city_code")
    @ColumnInfo(name = "city_code")
    String cityCode;

    public Airport(String timeZone, String name, Coordinates coordinates, @NonNull String code, NameTranslations name_translations, String countryCode, Boolean flightable, String cityCode) {
        this.timeZone = timeZone;
        this.name = name;
        this.coordinates = coordinates;
        this.code = code;
        this.name_translations = name_translations;
        this.countryCode = countryCode;
        this.flightable = flightable;
        this.cityCode = cityCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public NameTranslations getName_translations() {
        return name_translations;
    }

    public void setName_translations(NameTranslations name_translations) {
        this.name_translations = name_translations;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Boolean getFlightable() {
        return flightable;
    }

    public void setFlightable(Boolean flightable) {
        this.flightable = flightable;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
