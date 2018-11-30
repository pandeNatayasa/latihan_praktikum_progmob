package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailUserActivity extends AppCompatActivity{

    TextView tv_nama, tv_jasa, tv_gaji, tv_usia, tv_tanggal_lahir,
            tv_no_telp,tv_email,tv_status,tv_pendidikan,tv_alamat;
    Button btn_add_to_favorite;
    ApiService service;
    String user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        service=ApiClient.getApiService();

        btn_add_to_favorite=findViewById(R.id.btn_add_to_favorite);

        tv_nama=(TextView)findViewById(R.id.tv_user_name);
        tv_jasa=(TextView)findViewById(R.id.tv_user_pekerjaan);
        tv_gaji=(TextView)findViewById(R.id.tv_user_gaji);
        tv_usia=(TextView)findViewById(R.id.tv_user_usia);
        tv_tanggal_lahir=(TextView)findViewById(R.id.tv_user_tanggal_lahir);
        tv_no_telp=(TextView)findViewById(R.id.tv_user_noTelp);
        tv_email=(TextView)findViewById(R.id.tv_user_email);
        tv_status=(TextView)findViewById(R.id.tv_user_status);
        tv_pendidikan=(TextView)findViewById(R.id.tv_user_pendidikan);
        tv_alamat=(TextView)findViewById(R.id.tv_user_alamat);

        Bundle bundle = getIntent().getExtras();

        int id_data_jasa = bundle.getInt("id_data_jasa");
        String jasa = bundle.getString("jasa");
        Toast.makeText(DetailUserActivity.this," jasa :"+jasa,Toast.LENGTH_SHORT).show();
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

        btn_add_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.addToFavorite(id_user,id_data_jasa,user_token)
                        .enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Ditambahkan ke Favorite",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"Error"+t,Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}
