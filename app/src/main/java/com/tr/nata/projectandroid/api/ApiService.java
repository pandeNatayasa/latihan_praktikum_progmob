package com.tr.nata.projectandroid.api;

import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseDataJasa;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;
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
            @Field("tanggal_lahir")String tanggal_lahir,
            @Field("status")String status
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

    @GET("showDataJasaUser/{id}")
    Call<List<ResponseDataJasaUser>>showDataJasaUser(@Path("id") int id_user);

    @FormUrlEncoded
    @POST("store_data_jasa")
    Call<Response>newDataJasaUser(
            @Field("id_kategori") int id_kategori,
            @Field("id_user") int id_user,
            @Field("pekerjaan") String pekerjaan,
            @Field("estimasi_gaji") int estimasi_gaji,
            @Field("pengalaman_kerja") String pengalaman_kerja,
            @Field("usia") int usia,
            @Field("no_telp") String no_telp,
            @Field("email") String email,
            @Field("status") String status,
            @Field("alamat") String alamat
    );
}
