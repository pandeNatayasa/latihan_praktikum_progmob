package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataUser;
import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;

public class EditProfilleActivity extends AppCompatActivity {
    EditText et_name, et_email, et_jenis_kelamin, et_notelp, et_tanggal_lahir;
    Button btn_save;
    ApiService service;
    int id_user;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profille);

        service=ApiClient.getApiService();

        et_name=findViewById(R.id.et_name_profille);
        et_email=findViewById(R.id.et_email_profille);
        et_jenis_kelamin=findViewById(R.id.et_jk_profille);
        et_notelp=findViewById(R.id.et_notelp_profille);
        et_tanggal_lahir=findViewById(R.id.et_tanggal_lahir_profille);
        btn_save =findViewById(R.id.btn_save_edit_profille);

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        id_user = sharedPref.getInt("id_user_login",0);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        String email_user_login = sharedPref.getString("email_user_login","");
        String jk_user_login = sharedPref.getString("jk_user_login","");
        String no_telp_user_login = sharedPref.getString("no_telp_user_login","");
        String tanggal_lahir_user_login = sharedPref.getString("tanggal_lahir_user_login","");
        token = sharedPref.getString("user_token","");

        et_name.setText(nama_user_login);
        et_email.setText(email_user_login);
        et_jenis_kelamin.setText(jk_user_login);
        et_notelp.setText(no_telp_user_login);
        et_tanggal_lahir.setText(tanggal_lahir_user_login);

        Toolbar toolbar = findViewById(R.id.toolbarid_edit_profille);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String jenis_kelamin = et_jenis_kelamin.getText().toString();
                String no_telp = et_notelp.getText().toString();
                String tanggal_lahir = et_tanggal_lahir.getText().toString();

                service.update_profille(id_user,name,email,jenis_kelamin,no_telp,tanggal_lahir,token)
                        .enqueue(new Callback<ResponseLogin>() {
                            @Override
                            public void onResponse(Call<ResponseLogin> call, retrofit2.Response<ResponseLogin> response) {
                                if (response.isSuccessful()){
                                    SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);

                                    boolean status_login_boolean = sharedPref.getBoolean("status_login",true);
                                    String status_login_string = sharedPref.getString("status_login_string","");
                                    int id_user_login = sharedPref.getInt("id_user_login",0);
                                    String status_user = sharedPref.getString("status_user","");
                                    String user_token = sharedPref.getString("user_token","");
                                    String user_foto_profille = sharedPref.getString("user_foto_profille","");

                                    sharedPref.edit().clear().commit();

                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putBoolean("status_login",status_login_boolean);
                                    editor.putString("status_login_string", status_login_string);
                                    editor.putInt("id_user_login",id_user_login);
                                    editor.putString("nama_user_login", String.valueOf(response.body().getDataUser().getName()));
                                    editor.putString("email_user_login",String.valueOf(response.body().getDataUser().getEmail()));
                                    editor.putString("jk_user_login", String.valueOf(response.body().getDataUser().getJenisKelamin()));
                                    editor.putString("no_telp_user_login", String.valueOf(response.body().getDataUser().getNoTelp()));
                                    editor.putString("tanggal_lahir_user_login", String.valueOf(response.body().getDataUser().getTanggalLahir()));
                                    editor.putString("status_user",status_user);
                                    editor.putString("user_token",user_token);
                                    editor.putString("user_foto_profille",user_foto_profille);
                                    editor.apply();


                                    Intent intent = new Intent(getApplicationContext(),TryPerofilleActivity.class);
                                    intent.putExtra("Fragment_id",1);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(getApplicationContext(),"sukses tapi gagal",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"gagal : "+t,Toast.LENGTH_SHORT).show();
                            }
                        });

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
}
