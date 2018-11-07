package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.user;

import retrofit2.Call;

public class HomeActivity extends AppCompatActivity {

    TextView tv_namaUser;
    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_namaUser = findViewById(R.id.tv_nama);

        service=ApiClient.getApiService();

//        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//
//        String email = sharedPref.getString("email","");
//
//        Call<user> call = service.dataUser(email)
//                call.enqueue();


        Bundle bundle = getIntent().getExtras();
        tv_namaUser.setText(bundle.getString("namaUser"));
        tv_namaUser.setText(getIntent().getStringExtra("namaUser"));


    }
}
