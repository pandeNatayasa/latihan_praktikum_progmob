package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button tologin,toRegister;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tologin=findViewById(R.id.toLogin);
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        toRegister=findViewById(R.id.toRegister);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddUserActivity.class);
                startActivity(intent);
            }
        });

//        this.onBackPressed();
    }
//    @Override
//    public void onBackPressed(){
////            backpress = (backpress + 1);
//        SharedPreferences shared = getSharedPreferences("on_back_pressed", Context.MODE_PRIVATE);
//        int status = shared.getInt("status_login",0);
//        int backpress = 0;
//        if (status==0){
//            this.finish();
//        }
//    }


}
