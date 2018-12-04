package com.tr.nata.projectandroid.api;

import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseDataJasa;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;
import com.tr.nata.projectandroid.model.ResponseFavorite;
import com.tr.nata.projectandroid.model.ResponseKategori;
import com.tr.nata.projectandroid.model.ResponseLogin;
import com.tr.nata.projectandroid.model.ResponseStoreKategori;
import com.tr.nata.projectandroid.model.user;
//import okhttp3.ResponseBody;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
            @Field("email") String email,
            @Query("token") String token
            );

//    @FormUrlEncoded
    @GET("kategori")
    Call<ResponseKategori>getKategori(
            @Query("token") String token
    );

    @GET("showDataJasa/{id}")
    Call<ResponseDataJasa>showDataJasaByKategori(@Path("id") int id_kategori,@Query("token") String token);

    @GET("showDataJasaForAdmin/{id}")
    Call<ResponseDataJasa>showDataJasaByKategoriForAdmin(@Path("id") int id_kategori,@Query("token") String token);

    @GET("showDataJasaUser/{id}")
    Call<List<ResponseDataJasaUser>>showDataJasaUser(@Path("id") int id_user,@Query("token") String token);

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
            @Field("alamat") String alamat,
            @Query("token") String token
    );

    @FormUrlEncoded
    @POST("storeFavorite")
    Call<Response>addToFavorite(
            @Field("id_user") int id_user,
            @Field("id_data_jasa") int id_data_jasa,
            @Query("token") String token
    );

    @GET("showFavorite/{id}")
    Call<List<ResponseFavorite>>showFavorite(@Path("id") int id_user,@Query("token") String token);

//    @FormUrlEncoded
//    @POST("storeKategori")
//    Call<ResponseStoreKategori>addKategori(
//            @Field("kategori") String kategori,
//            @Field("logo_kategori") String logo_kategori,
//            @Query("token") String token
//    );

    @Multipart
    @POST("storeKategori")
    Call<ResponseStoreKategori>addKategori(
            @Part("kategori") RequestBody kategori,
            @Part MultipartBody.Part logo_kategori,
            @Part("token") RequestBody token
    );

    @DELETE("delete_kategori/{id}")
    Call<ResponseStoreKategori>deleteKategori(@Path("id") int id_kategori,@Query("token") String token);

//    @FormUrlEncoded
//    @POST("update_kategori/{id}")
//    Call<ResponseStoreKategori>updateKategori(
//            @Path("id") int id_kategori,
//            @Field("kategori") String kategori,
//            @Field("logo_kategori") String logo_kategori,
//            @Query("token") String token
//    );

    @Multipart
    @POST("update_kategori/{id}")
    Call<ResponseStoreKategori>updateKategori(
            @Part("id") RequestBody id_kategori,
            @Part("kategori") RequestBody kategori,
            @Part("logo_kategori") MultipartBody.Part logo_kategori,
            @Part("token") RequestBody token
    );

}
