package com.tr.nata.projectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class AddUserActivity extends AppCompatActivity{

    private Button btnAdd;
    private EditText etUserName,etUserEmail,etUserPassword,etUserJenisKelamin, etUserNoTelp, etUserTanggalLahir;

    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("Add User");

        service = ApiClient.getService();

        etUserName = findViewById(R.id.etUserName);
        etUserEmail = findViewById(R.id.etUserMail);
        etUserPassword=findViewById(R.id.etUserPassword);
        etUserJenisKelamin=findViewById(R.id.etUserJenisKelamin);
        etUserNoTelp=findViewById(R.id.etUserNoTelp);
        etUserTanggalLahir=findViewById(R.id.etUserTanggalLahir);

        btnAdd=findViewById(R.id.register);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }

    private void addUser(){
        String name = etUserName.getText().toString();
        String email = etUserEmail.getText().toString();
        String password = etUserPassword.getText().toString();
        String jenis_kelamin = etUserJenisKelamin.getText().toString();
        String no_telp=etUserNoTelp.getText().toString();
        String tanggal_lahir=etUserTanggalLahir.getText().toString();

        service.addUser(name,email,password,jenis_kelamin,no_telp,tanggal_lahir)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AddUserActivity.this,"Success",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(AddUserActivity.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(AddUserActivity.this,"Error"+t,Toast.LENGTH_SHORT).show();
                    }
                });
        Toast.makeText(this,"Name : "+name+", email : "+email,Toast.LENGTH_SHORT).show();
    }
}
