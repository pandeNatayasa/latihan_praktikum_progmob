package com.tr.nata.projectandroid;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.userLogin;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button sigin;
    ApiService service;

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        service= ApiClient.getService();

        etEmail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);

        sigin=findViewById(R.id.signIn);
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

//                Call<userLogin> call = service.login(email,password);

                service.login(email,password)
                .enqueue(new Callback<userLogin>() {
                    @Override
                    public void onResponse(Call<userLogin> call, retrofit2.Response<userLogin> response) {
                        Toast.makeText(getApplicationContext()," "+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                        if (response.body().getStatus()){
                            Toast.makeText(getApplicationContext(), "login berhasil",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"login gagal",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<userLogin> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"login gagal koneksi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void login(){

    }
}
