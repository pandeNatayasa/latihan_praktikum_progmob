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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseLogin;
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

public class UpdateFotoProfille extends AppCompatActivity {
    String path;
    Bundle bundle;
    String str_user_token,filename;
    int int_id_user;

    EditText et_nama_new_foto;
    ImageView img_new_foto;
    Button btn_update_foto;
    ApiService serviceUpdateFotoProfille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_foto_profille);

        serviceUpdateFotoProfille=ApiClient.getApiService();

        bundle = getIntent().getExtras();
        path = bundle.getString("path");
        Toast.makeText(UpdateFotoProfille.this," path in update : "+path,Toast.LENGTH_SHORT).show();
        filename = bundle.getString("filename");

        et_nama_new_foto=findViewById(R.id.et_foto_profille);
        img_new_foto=findViewById(R.id.img_new_foto_profille);
        btn_update_foto=findViewById(R.id.btn_update_foto_profille);

        Toolbar toolbar = findViewById(R.id.toolbarid_new_foto_profille);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        str_user_token = sharedPref.getString("user_token","");
        int_id_user=sharedPref.getInt("id_user_login",0);

        et_nama_new_foto.setText(filename);
        Glide.with(UpdateFotoProfille.this).load(path).into(img_new_foto);

        btn_update_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = getIntent().getExtras();
                path = bundle.getString("path");

                Toast.makeText(UpdateFotoProfille.this,"will be update",Toast.LENGTH_SHORT).show();

                RequestBody id_user = RequestBody.create(MediaType.parse("text/plain"),String.valueOf(int_id_user));
                RequestBody user_token = RequestBody.create(MediaType.parse("text/plain"),str_user_token);
                File file = new File(path);
                RequestBody requestFile = RequestBody.create(MediaType.parse("foto_profille"),file);
                MultipartBody.Part foto_profille = MultipartBody.Part.createFormData("foto_profille", file.getName(),requestFile);

                callApiUpdateFotoProfille(id_user,foto_profille,user_token);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callApiUpdateFotoProfille(RequestBody id_user, MultipartBody.Part foto_profille,RequestBody user_token){
        serviceUpdateFotoProfille.updateFotoProfille(foto_profille,user_token)
                .enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, retrofit2.Response<ResponseLogin> response) {
                        if(response.isSuccessful()){
                            SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("user_foto_profille",response.body().getDataUser().getFoto_profille());
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(),TryPerofilleActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(UpdateFotoProfille.this,"will be update actualy but error",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        Toast.makeText(UpdateFotoProfille.this,"error : "+t,Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
