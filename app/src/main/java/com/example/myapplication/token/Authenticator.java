package com.example.myapplication.token;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class Authenticator implements okhttp3.Authenticator {
    String token;

    public Authenticator(@NonNull String token) {
        this.token = token;
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
        return response.request().newBuilder()
                .addHeader("Authorization", token)
                .build();
    }
}
