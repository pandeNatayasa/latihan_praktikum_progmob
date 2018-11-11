package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//                Boolean status_login = sharedPref.getBoolean("status_login",false);
                String status_login = sharedPref.getString("status_login_string","");
                String status_user = sharedPref.getString("status_user","");

                Toast.makeText(SplashScreenActivity.this,"status : "+status_login,Toast.LENGTH_SHORT).show();

                if (status_login.equals("true")){
                    if (status_user.equals("1")){
                        Intent homeIntent = new Intent(SplashScreenActivity.this,HomeActivity.class);
                        startActivity(homeIntent);
                    }else {
                        Intent homeIntent = new Intent(SplashScreenActivity.this,HomeAdminActivity.class);
                        startActivity(homeIntent);
                    }
                }else {
                    Intent homeIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(homeIntent);
                }

                finish();
            }
        },SPLASH_TIME_OUT);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
