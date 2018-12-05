package com.tr.nata.projectandroid;

import android.content.Context;
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

public class EditProfilleActivity extends AppCompatActivity {
    EditText et_name, et_email, et_jenis_kelamin, et_notelp, et_tanggal_lahir;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profille);

        et_name=findViewById(R.id.et_name_profille);
        et_email=findViewById(R.id.et_email_profille);
        et_jenis_kelamin=findViewById(R.id.et_jk_profille);
        et_notelp=findViewById(R.id.et_notelp_profille);
        et_tanggal_lahir=findViewById(R.id.et_tanggal_lahir_profille);
        btn_save =findViewById(R.id.btn_save_edit_profille);

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        String email_user_login = sharedPref.getString("email_user_login","");
        String jk_user_login = sharedPref.getString("jk_user_login","");
        String no_telp_user_login = sharedPref.getString("no_telp_user_login","");
        String tanggal_lahir_user_login = sharedPref.getString("tanggal_lahir_user_login","");

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
                Toast.makeText(getApplicationContext(),"will be update soon",Toast.LENGTH_SHORT).show();
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
