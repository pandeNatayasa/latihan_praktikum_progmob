package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.ResponseLogin;


import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button sigin;
    ApiService service, service1;
    TextView tv_register;

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        service= ApiClient.getApiService();
        service1 =ApiClient.getApiService();

        etEmail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);
        tv_register=findViewById(R.id.tv_register);

        sigin=findViewById(R.id.signIn);
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_login();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(LoginActivity.this,AddUserActivity.class);
                startActivity(intentRegister);
            }
        });

    }

    public void add_login(){
        final String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Call<ResponseLogin> call = service.login(email, password);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, retrofit2.Response<ResponseLogin> response) {

                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext()," "+response.body().isStatus(),Toast.LENGTH_SHORT).show();
                    if (response.body().isStatus()){
//                        Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean("status_login",response.body().isStatus());
                        editor.putString("status_login_string", String.valueOf(response.body().isStatus()));
//                        editor.putString("id_user_login",String.valueOf(response.body().getDataUser().getId()));
                        editor.putInt("id_user_login",response.body().getDataUser().getId());
                        editor.putString("nama_user_login", String.valueOf(response.body().getDataUser().getName()));
                        editor.putString("email_user_login",String.valueOf(response.body().getDataUser().getEmail()));
                        editor.putString("jk_user_login", String.valueOf(response.body().getDataUser().getJenisKelamin()));
                        editor.putString("no_telp_user_login", String.valueOf(response.body().getDataUser().getNoTelp()));
                        editor.putString("tanggal_lahir_user_login", String.valueOf(response.body().getDataUser().getTanggalLahir()));
                        editor.putString("status_user",String.valueOf(response.body().getDataUser().getStatusUser()));
                        editor.putString("user_token",response.body().getToken());
                        editor.putString("user_foto_profille",response.body().getDataUser().getFoto_profille());
                        editor.apply();

                        String status = sharedPref.getString("status_login_string","");
//                        Toast.makeText(getApplicationContext(),"status : "+status,Toast.LENGTH_SHORT).show();

                        String token = response.body().getToken();
                        Toast.makeText(getApplicationContext(),"token : "+token,Toast.LENGTH_SHORT).show();

                        String nama = response.body().getDataUser().getName();
                        String statusUser = response.body().getDataUser().getStatusUser();
//                        Toast.makeText(getApplicationContext(),"nama : "+nama,Toast.LENGTH_SHORT).show();

                        if (statusUser.equals("1")){
//                            Bundle bundle = new Bundle();
//                            bundle.putString("namaUser", nama);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"login admin",Toast.LENGTH_SHORT).show();
                            Intent intent_admin=new Intent(LoginActivity.this,HomeAdminActivity.class);
                            startActivity(intent_admin);
                            finish();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"login gagal",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Username atau Password Salah",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"login gagal koneksi",Toast.LENGTH_SHORT).show();
            }
        });

    }
//
//    public void data_user(){
//        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//
//        String email = sharedPref.getString("email","");
//
//        Toast.makeText(getApplicationContext(),"email :"+email,Toast.LENGTH_SHORT).show();
//
////        Call<user> call2 = ;
//        service1.dataUser(email)
//                .enqueue(new Callback<user>() {
//                    @Override
//                    public void onResponse(Call<user> call, retrofit2.Response<user> response) {
//                        if (response.isSuccessful()){
//                            String nama = response.body().getName();
//                            Toast.makeText(getApplicationContext(),"nama : "+ nama,Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(getApplicationContext(),"data user gagal",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<user> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),"login gagal koneksi" + t,Toast.LENGTH_SHORT).show();
//
//                        tv_pesan.setText("Eror"+t.toString());
//                    }
//                });
//
////        service1.dataUser(email)
//    }


}
