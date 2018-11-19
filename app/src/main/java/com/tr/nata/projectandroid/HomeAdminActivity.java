package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeAdminActivity extends AppCompatActivity {

    Button btn_logout,btn_toAddKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        btn_logout=(Button)findViewById(R.id.btn_logout);
        btn_toAddKategori=findViewById(R.id.btn_toAddKategori);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean login = false;
                SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("status_login",login);
                editor.putString("status_login_string", String.valueOf(login));
                editor.apply();

                Intent intent = new Intent(HomeAdminActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_toAddKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddKategori = new Intent(HomeAdminActivity.this,AddKategoriAdminActivity.class);
                startActivity(toAddKategori);
            }
        });

    }
}
