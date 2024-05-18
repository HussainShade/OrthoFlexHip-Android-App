package com.example.orthoflexhip.api;

import com.example.orthoflexhip.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://3c61-103-28-246-159.ngrok-free.app/orthoflex_hip/";
    public static final String NETWORK_URL = "http://192.168.1.7/orthoflex_hip/";
    private static ApiService instance;

    public static ApiService getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(ApiService.class);
        }
        return instance;
    }
}