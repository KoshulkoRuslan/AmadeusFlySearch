
package com.example.myapplication.apimodels.flyOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dictionaries {

    @SerializedName("locations")
    @Expose
    private Object locations;
    @SerializedName("aircraft")
    @Expose
    private Aircraft_ aircraft;
    @SerializedName("currencies")
    @Expose
    private Currencies currencies;
    @SerializedName("carriers")
    @Expose
    private Carriers carriers;

    public Object getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public Aircraft_ getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft_ aircraft) {
        this.aircraft = aircraft;
    }

    public Currencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
    }

    public Carriers getCarriers() {
        return carriers;
    }

    public void setCarriers(Carriers carriers) {
        this.carriers = carriers;
    }

}
