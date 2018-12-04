package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.ResponseStoreKategori;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateKategoriAdminActivity extends AppCompatActivity {

    DatabaseHelper mydb;

    private Button btn_addkategori,btn_pilih_image;
    private EditText et_nama_kategori,et_logo_kategori;
    private TextView tv_update_toolbar;
    ImageView img_new_logo_kategori;

    String str_user_token,nama_kategori,logo_kategori;
    int id_kategori;
    private ArrayList<AlbumFile> mAlbumFiles;
    String path;
    ApiService serviceUpdateKategori;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kategory_admin);

        serviceUpdateKategori=ApiClient.getApiService();

        btn_addkategori=findViewById(R.id.btn_addkategori);
        et_nama_kategori=findViewById(R.id.et_addkategori);
        et_logo_kategori=findViewById(R.id.et_logo_kategori);
        img_new_logo_kategori=findViewById(R.id.img_new_logo_kategori);
        btn_pilih_image=findViewById(R.id.btn_pilih_image_logo);
        btn_addkategori.setText("Update Kategori");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid_add_kategori);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_update_toolbar=findViewById(R.id.tv_kategori_admin_toolbar);
        tv_update_toolbar.setText("Update Kategori");


        bundle = getIntent().getExtras();
        nama_kategori = bundle.getString("namaKategori");
        logo_kategori=bundle.getString("logoKategori");
        id_kategori=bundle.getInt("idKategori");

        et_nama_kategori.setText(nama_kategori);
        et_logo_kategori.setText(logo_kategori);
        String url = "http://172.17.100.2:8000"+logo_kategori;
        Glide.with(this).load(url).into(img_new_logo_kategori);

//        mydb=new DatabaseHelper(this);
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        str_user_token = sharedPref.getString("user_token","");

        btn_pilih_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        updateDataKategori();

    }

    public void updateDataKategori(){
        btn_addkategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                boolean isInserted = mydb.insertDataKategori(et_nama_kategori.getText().toString());
//                if (isInserted=true){
//                    Toast.makeText(AddKategoriAdminActivity.this,"Kategori berhasil di simpan",Toast.LENGTH_SHORT).show();
//
//                }else {
//                    Toast.makeText(AddKategoriAdminActivity.this,"Kategori gagal di simpan",Toast.LENGTH_SHORT).show();
//                }
                bundle = getIntent().getExtras();
                String id_kategori=String.valueOf(bundle.getInt("idKategori"));
                logo_kategori=bundle.getString("logoKategori");
                String str_kategori = et_nama_kategori.getText().toString();

                RequestBody idKategori = RequestBody.create(MediaType.parse("text/plain"),id_kategori);
                RequestBody kategori = RequestBody.create(MediaType.parse("text/plain"),str_kategori);
                RequestBody user_token = RequestBody.create(MediaType.parse("text/plain"),str_user_token);
                File file = new File(logo_kategori);
                RequestBody requestFile = RequestBody.create(MediaType.parse("logo_kategori"),file);
                MultipartBody.Part logo_kategori = MultipartBody.Part.createFormData("logo_kategori", file.getName(),requestFile);

                callApiUpdateKategori(idKategori,kategori,logo_kategori,user_token);
            }
        });
    }
    private void callApiUpdateKategori(RequestBody id_kategori,RequestBody kategori, MultipartBody.Part logo_kategori,RequestBody user_token){
        serviceUpdateKategori.updateKategori(id_kategori,kategori,logo_kategori,user_token)
                .enqueue(new Callback<ResponseStoreKategori>() {
                    @Override
                    public void onResponse(Call<ResponseStoreKategori> call, Response<ResponseStoreKategori> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"sukses mengupdate kategori "+nama_kategori,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(updateKategoriAdminActivity.this,HomeAdminActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"gagal mengupdate kategori "+nama_kategori,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseStoreKategori> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error"+t,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Select picture, from album.
     */
    private void selectImage() {
        Album.image(this)
                .singleChoice()
                .camera(true)
                .widget(
                        Widget.newDarkBuilder(this)
                                .build()
                )
                .onResult((Action<ArrayList<AlbumFile>>) result -> {
                    path = result.get(0).getPath();
                    Toast.makeText(updateKategoriAdminActivity.this,"path : "+path,Toast.LENGTH_SHORT).show();
                    Glide.with(updateKategoriAdminActivity.this).load(path).into(img_new_logo_kategori);
                    String filename = path.substring(path.lastIndexOf("/")+1);
                    et_logo_kategori.setText(filename);
                    mAlbumFiles = result;
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Toast.makeText(updateKategoriAdminActivity.this, "cancell", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
