package com.example.myapplication.token;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AmadeusTokenAPI {

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("v1/security/oauth2/token")
    Call<ResponseToken> getToken(@FieldMap HashMap<String, String> body);

}
