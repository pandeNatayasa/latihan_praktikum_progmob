package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailUserInAdminActivity extends AppCompatActivity {
    TextView tv_nama, tv_jasa, tv_gaji, tv_usia, tv_tanggal_lahir,
            tv_no_telp,tv_email,tv_status,tv_pendidikan,tv_alamat;
    Button btn_add_to_favorite;
    ApiService service;
    String user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_in_admin);

        service=ApiClient.getApiService();

        tv_nama=(TextView)findViewById(R.id.tv_user_name_inadmin);
        tv_jasa=(TextView)findViewById(R.id.tv_user_pekerjaan_inadmin);
        tv_gaji=(TextView)findViewById(R.id.tv_user_gaji_inadmin);
        tv_usia=(TextView)findViewById(R.id.tv_user_usia_inadmin);
        tv_tanggal_lahir=(TextView)findViewById(R.id.tv_user_tanggal_lahir_inadmin);
        tv_no_telp=(TextView)findViewById(R.id.tv_user_noTelp_inadmin);
        tv_email=(TextView)findViewById(R.id.tv_user_email_inadmin);
        tv_status=(TextView)findViewById(R.id.tv_user_status_inadmin);
        tv_pendidikan=(TextView)findViewById(R.id.tv_user_pendidikan_inadmin);
        tv_alamat=(TextView)findViewById(R.id.tv_user_alamat_inadmin);

        Toolbar toolbar = findViewById(R.id.toolbarid_detail_profille_in_admin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        int id_data_jasa = bundle.getInt("id_data_jasa");
        String jasa = bundle.getString("jasa");
        Toast.makeText(DetailUserInAdminActivity.this," jasa :"+jasa,Toast.LENGTH_SHORT).show();
//        tv_jasa.setText(jasa);
        tv_nama.setText(bundle.getString("nama"));
        tv_jasa.setText(bundle.getString("jasa"));
        tv_gaji.setText(bundle.getString("gaji"));
        tv_usia.setText(bundle.getString("usia"));
        tv_tanggal_lahir.setText(bundle.getString("tanggal_lahir"));
        tv_no_telp.setText(bundle.getString("no_telp"));
        tv_email.setText(bundle.getString("email"));
        tv_status.setText(bundle.getString("status"));
        tv_pendidikan.setText(bundle.getString("pendidikan"));
        tv_alamat.setText(bundle.getString("alamat"));

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//        Integer id_user_login = Integer.parseInt(sharedPref.getString("id_user_login",""));
        Integer id_user = sharedPref.getInt("id_user_login", 0);
        user_token = sharedPref.getString("user_token","");

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
