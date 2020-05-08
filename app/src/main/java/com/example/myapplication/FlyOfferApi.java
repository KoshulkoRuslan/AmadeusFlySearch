package com.example.myapplication;

import androidx.lifecycle.LiveData;

import com.example.myapplication.apimodels.flyOffer.FlySearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface FlyOfferApi {
    @Headers({"Content-Type: application/vnd.amadeus+json"})
    @GET("v2/shopping/flight-offers")
    Call<FlySearchModel> getFlyOffer(@Query("originLocationCode") String originLocationCode ,
                                              @Query("destinationLocationCode") String destinationLocationCode ,
                                              @Query("departureDate") String departureDate ,
                                              @Query("returnDate") String returnDate,
                                              @Query("adults") int adults ,
                                              @Query("children") int children,
                                              @Query("infants") int infants,
                                              @Query("travelClass") String travelClass,
                                              @Query("currencyCode") String currencyCode);

}
