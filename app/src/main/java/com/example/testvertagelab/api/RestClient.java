package com.example.testvertagelab.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static RestClient sInstance = new RestClient();

    private ServerApi service;
    private Retrofit retrofit;

    private final static String API_URL = "https://2fjd9l3x1l.api.quickmocker.com/";

    private RestClient (){
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(API_URL)
                .build();

        service = retrofit.create(ServerApi.class);

    }



    public static RestClient getsInstance() {
        return sInstance;
    }

    public ServerApi getService() {
        return service;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
