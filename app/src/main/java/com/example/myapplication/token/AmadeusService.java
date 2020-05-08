package com.example.myapplication.token;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.StartActivity;
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class AmadeusService  {

    private static  AmadeusService instance;
    private StartActivity.TokenStatus tokenStatus;
    private final String baseUrl = "https://test.api.amadeus.com/";
    private static final String TAG = "AmadeusServer";
    private long expiresTime;
    private SharedPreferences sharedPreferences;
    private ResponseToken responseToken;
    private String token;
    private Retrofit retrofit;
    Retrofit amadeusRetrofit;

    private AmadeusService(Context context, @Nullable StartActivity.TokenStatus tokenStatus){
            sharedPreferences = context.getSharedPreferences(BuildConfig.TOKEN_BASE,MODE_PRIVATE);
            token = sharedPreferences.getString("token", null);
            Log.d(TAG, "AmadeusService: token" + token);
            expiresTime = sharedPreferences.getLong("expiresTime",0);
            this.tokenStatus = tokenStatus;
    }


    private  @NonNull String refreshToken(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmadeusTokenAPI tokenAPI = retrofit.create(AmadeusTokenAPI.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("grant_type", "client_credentials");
        hashMap.put("client_id", BuildConfig.API_KEY);
        hashMap.put("client_secret", BuildConfig.API_SECRET);

        Thread thread = new Thread(() -> {
            try {
                responseToken = tokenAPI.getToken(hashMap).execute().body();
                if (responseToken != null){
                    token = responseToken.getTokenType() + " " + responseToken.getAccessToken();
                    expiresTime = responseToken.getExpiresIn() * 1000 + System.currentTimeMillis();
                    sharedPreferences.edit().putString("token", token).apply();
                    sharedPreferences.edit().putLong("expiresTime", expiresTime).apply();
                    token();
                }
            } catch (IOException e) {
                Log.d(TAG, "Exception: " + e.getMessage());
            }
        });
        thread.start();

        return token;
    }

    private synchronized String token(){

        if (token == null || token.isEmpty() || expiresTime < System.currentTimeMillis()){
            Log.d(TAG, "token: токен нулевой");
            return refreshToken();
        }
        else  {
            if (tokenStatus != null)
            tokenStatus.tokenIsReady();
        }
        return token;
    }

    public Retrofit getAmadeusRetrofit(){
        if (amadeusRetrofit == null){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .authenticator(new Authenticator(token()))
                    .addInterceptor(loggingInterceptor)
                    .callTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

            amadeusRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            return amadeusRetrofit;
        }


        return amadeusRetrofit;
    }

    public void refreshAmadeusRetrofit(){

    }

    public static synchronized AmadeusService getAmadeusService(Context context, StartActivity.TokenStatus tokenStatus){
        if (instance == null){
            instance = new AmadeusService(context,tokenStatus);
        }
        instance.tokenStatus = tokenStatus;
        instance.token();
        return instance;
    }

    public Boolean isTokenNotNull(){
        if (token == null || token.isEmpty() || expiresTime < System.currentTimeMillis()){
            Log.d(TAG, "token: токен нулевой");
            return false;
        }
        return true;
    }
}
