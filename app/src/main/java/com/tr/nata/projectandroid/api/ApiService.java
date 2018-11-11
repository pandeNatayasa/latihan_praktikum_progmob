package com.tr.nata.projectandroid.api;

import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseDataJasa;
import com.tr.nata.projectandroid.model.ResponseKategori;
import com.tr.nata.projectandroid.model.ResponseLogin;
import com.tr.nata.projectandroid.model.user;
//import okhttp3.ResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin>login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<Response>addUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("no_telp") String no_telp,
            @Field("tanggal_lahir")String tanggal_lahir
            );

    @FormUrlEncoded
    @POST("data_user")
    Call<user>dataUser(
            @Field("email") String email
            );

//    @FormUrlEncoded
    @GET("kategori")
    Call<ResponseKategori>getKategori();

    @GET("showDataJasa/{id}")
    Call<ResponseDataJasa>showDataJasaByKategori(@Path("id") int id_kategori);

}
