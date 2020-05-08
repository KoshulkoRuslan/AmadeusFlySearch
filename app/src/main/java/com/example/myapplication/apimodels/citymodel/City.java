package com.example.myapplication.apimodels.citymodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity
public class City {
    @SerializedName("time_zone")
    @ColumnInfo(name = "time_zone")
    String timeZone;

    String name;

    @Embedded
    Coordinates coordinates;

    //Код города, например MOW, LED (код города и аэропорта совпадает)
    @NonNull
    @PrimaryKey(autoGenerate = false)
    String code;

    @Embedded
    Cases cases;

    @Embedded
    NameTranslations name_translations;

    @SerializedName("country_code")
    @ColumnInfo(name = "country_code")
    String countryCode;

    public City(String timeZone, String name, Coordinates coordinates, @NonNull String code, Cases cases, NameTranslations name_translations, String countryCode) {
        this.timeZone = timeZone;
        this.name = name;
        this.coordinates = coordinates;
        this.code = code;
        this.cases = cases;
        this.name_translations = name_translations;
        this.countryCode = countryCode;
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

    public Cases getCases() {
        return cases;
    }

    public void setCases(Cases cases) {
        this.cases = cases;
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
}
