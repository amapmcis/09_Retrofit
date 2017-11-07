package com.example.f1.a09_retrofit.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiFactory {

    public static RetrofitApiInterface getRetrofitApiInterface() {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitApiInterface.class);
    }
}