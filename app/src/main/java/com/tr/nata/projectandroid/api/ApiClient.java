package com.tr.nata.projectandroid.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = null;

    public static ApiService getService(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.17.100.2:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create() )
                .build();

        return retrofit.create(ApiService.class);
    }
}
