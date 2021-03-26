package com.example.testvertagelab.api;

import com.example.testvertagelab.model.Places;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {
    @GET("/kyiv/places")
    Call<Places> getDate();
}
