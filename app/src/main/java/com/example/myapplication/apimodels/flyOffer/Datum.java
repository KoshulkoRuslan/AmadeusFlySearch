
package com.example.myapplication.apimodels.flyOffer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("instantTicketingRequired")
    @Expose
    private Boolean instantTicketingRequired;
    @SerializedName("nonHomogeneous")
    @Expose
    private Boolean nonHomogeneous;
    @SerializedName("oneWay")
    @Expose
    private Boolean oneWay;
    @SerializedName("lastTicketingDate")
    @Expose
    private String lastTicketingDate;
    @SerializedName("numberOfBookableSeats")
    @Expose
    private Integer numberOfBookableSeats;
    @SerializedName("itineraries")
    @Expose
    private List<Itinerary> itineraries = null;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("pricingOptions")
    @Expose
    private PricingOptions pricingOptions;
    @SerializedName("validatingAirlineCodes")
    @Expose
    private List<String> validatingAirlineCodes = null;
    @SerializedName("travelerPricings")
    @Expose
    private List<TravelerPricing> travelerPricings = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getInstantTicketingRequired() {
        return instantTicketingRequired;
    }

    public void setInstantTicketingRequired(Boolean instantTicketingRequired) {
        this.instantTicketingRequired = instantTicketingRequired;
    }

    public Boolean getNonHomogeneous() {
        return nonHomogeneous;
    }

    public void setNonHomogeneous(Boolean nonHomogeneous) {
        this.nonHomogeneous = nonHomogeneous;
    }

    public Boolean getOneWay() {
        return oneWay;
    }

    public void setOneWay(Boolean oneWay) {
        this.oneWay = oneWay;
    }

    public String getLastTicketingDate() {
        return lastTicketingDate;
    }

    public void setLastTicketingDate(String lastTicketingDate) {
        this.lastTicketingDate = lastTicketingDate;
    }

    public Integer getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    public void setNumberOfBookableSeats(Integer numberOfBookableSeats) {
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(PricingOptions pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public List<String> getValidatingAirlineCodes() {
        return validatingAirlineCodes;
    }

    public void setValidatingAirlineCodes(List<String> validatingAirlineCodes) {
        this.validatingAirlineCodes = validatingAirlineCodes;
    }

    public List<TravelerPricing> getTravelerPricings() {
        return travelerPricings;
    }

    public void setTravelerPricings(List<TravelerPricing> travelerPricings) {
        this.travelerPricings = travelerPricings;
    }

}
