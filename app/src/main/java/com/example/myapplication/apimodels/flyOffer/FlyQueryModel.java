package com.example.myapplication.apimodels.flyOffer;

import java.util.Calendar;

public class FlyQueryModel {
    private String originLocationCode ;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private int adults;
    private int children;
    private int infants;
    private String travelClass;
    private String currencyCode;

    public FlyQueryModel() {
        defaultInit();
    }

    public FlyQueryModel(String originLocationCode, String destinationLocationCode, String departureDate, String returnDate, int adults, int children, int infants, String travelClass, String currencyCode) {
        this.originLocationCode = originLocationCode;
        this.destinationLocationCode = destinationLocationCode;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
        this.travelClass = travelClass;
        this.currencyCode = currencyCode;
    }

    public String getOriginLocationCode() {
        return originLocationCode;
    }

    public void setOriginLocationCode(String originLocationCode) {
        this.originLocationCode = originLocationCode;
    }

    public String getDestinationLocationCode() {
        return destinationLocationCode;
    }

    public void setDestinationLocationCode(String destinationLocationCode) {
        this.destinationLocationCode = destinationLocationCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    private void defaultInit(){
        originLocationCode = "LED";
        destinationLocationCode = "SVO";
        departureDate = "2020-12-12";
        returnDate = "2020-12-20";
        adults = 1;
        children = 0;
        infants = 0;
        travelClass = "ECONOMY";
        currencyCode = "RUB";
    }
}
