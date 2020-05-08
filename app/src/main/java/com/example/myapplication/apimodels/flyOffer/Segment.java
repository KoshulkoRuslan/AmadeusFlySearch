
package com.example.myapplication.apimodels.flyOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment {

    @SerializedName("departure")
    @Expose
    private Departure departure;
    @SerializedName("arrival")
    @Expose
    private Arrival arrival;
    @SerializedName("carrierCode")
    @Expose
    private String carrierCode;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("aircraft")
    @Expose
    private Aircraft aircraft;
    @SerializedName("operating")
    @Expose
    private Operating operating;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("numberOfStops")
    @Expose
    private Integer numberOfStops;
    @SerializedName("blacklistedInEU")
    @Expose
    private Boolean blacklistedInEU;

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Operating getOperating() {
        return operating;
    }

    public void setOperating(Operating operating) {
        this.operating = operating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(Integer numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public Boolean getBlacklistedInEU() {
        return blacklistedInEU;
    }

    public void setBlacklistedInEU(Boolean blacklistedInEU) {
        this.blacklistedInEU = blacklistedInEU;
    }

}
