package com.tr.nata.projectandroid.api;

import com.google.gson.Gson;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiClient {

//    public static Retrofit retrofit = null;
//
//    public static ApiService getService(){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.17.100.2:8000/api/v1/")
//                .addConverterFactory(GsonConverterFactory.create() )
//                .build();
//
//        return retrofit.create(ApiService.class);
//    }

    private static ApiService INSTANCE;
    private static final String BASE_URL = "http://172.17.100.2:8000/api/v1/";

    public static ApiService getApiService(){
        if (INSTANCE==null){
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .client(okHttpClientBuilder.build())
                    .build();

            INSTANCE = retrofit.create(ApiService.class);
        }
        return INSTANCE;
    }

    public static ApiService getKeepApiServiceWithToken(String token){
        if (INSTANCE==null){
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient(token))
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

            INSTANCE = retrofit.create(ApiService.class);
        }
        return INSTANCE;
    }

    public static OkHttpClient getOkHttpClient(String token){
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(chain ->{
                    Request originalRequest = chain.request();
                    HttpUrl originalUrl = originalRequest.url();
                    HttpUrl newUrl = originalUrl.newBuilder()
                            .addQueryParameter("access_token",token)
                            .build();

                    Request newRequest = originalRequest.newBuilder()
                            .url(newUrl)
                            .build();

                    return chain.proceed(newRequest);
                })
                .build();
    }
}
