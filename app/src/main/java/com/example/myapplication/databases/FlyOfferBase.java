package com.example.myapplication.databases;

import android.content.Context;

import com.example.myapplication.token.AmadeusService;

import retrofit2.Retrofit;

public class FlyOfferBase {
    static Retrofit instance;

    public static Retrofit getInstance(Context context){
        if (instance == null){
            synchronized (FlyOfferBase.class){
                instance = AmadeusService.getAmadeusService(context,null).getAmadeusRetrofit();
            }
        }
        return instance;
    }
}
