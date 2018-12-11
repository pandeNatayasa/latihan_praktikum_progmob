package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseChekFavorite;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailUserActivity extends AppCompatActivity{

    TextView tv_nama, tv_jasa, tv_gaji, tv_usia, tv_tanggal_lahir,
            tv_no_telp,tv_email,tv_status,tv_pendidikan,tv_alamat;
    LinearLayout btn_add_to_favorite;
    ImageView img_add_to_favorite;
    ApiService service,serviceCheckFavorite;
    String user_token;
    int jumlah=0;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid_detail_profille_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mydb=new DatabaseHelper(this);

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//        Integer id_user_login = Integer.parseInt(sharedPref.getString("id_user_login",""));
        int id_user = sharedPref.getInt("id_user_login", 0);
        user_token = sharedPref.getString("user_token","");

        Bundle bundle = getIntent().getExtras();
        int id_data_jasa = bundle.getInt("id_data_jasa");
        String jasa = bundle.getString("jasa");

        service=ApiClient.getApiService();
        serviceCheckFavorite=ApiClient.getApiService();

        btn_add_to_favorite=findViewById(R.id.btn_add_to_favorite);
        img_add_to_favorite=findViewById(R.id.img_add_to_favorite);

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

//        Toast.makeText(DetailUserActivity.this," jasa :"+jasa,Toast.LENGTH_SHORT).show();
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

//        cekFavorite(id_user,id_data_jasa,user_token);

        Toast.makeText(this,"koneksi "+isConnected(),Toast.LENGTH_SHORT).show();
        if (isConnected()){
            addToFavorite(id_user,id_data_jasa,user_token);
        }else {
            cekFavoriteLokal(id_user,id_data_jasa);
        }
    }

    public int cekFavorite(int id_user, int id_data_jasa,String user_token){

        serviceCheckFavorite.checkFavorite(id_user,id_data_jasa,user_token)
                .enqueue(new Callback<ResponseChekFavorite>() {
                    @Override
                    public void onResponse(Call<ResponseChekFavorite> call, retrofit2.Response<ResponseChekFavorite> response) {
                        if (response.isSuccessful()){
                            jumlah = response.body().getJumlahFavorite();
                            if (response.body().getJumlahFavorite() > 0){
                                img_add_to_favorite.setImageResource(R.drawable.ic_check_blue);
                            }else {
                                img_add_to_favorite.setImageResource(R.drawable.ic_add_black_24dp);
//                                addToFavorite(id_user,id_data_jasa,user_token);
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseChekFavorite> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error"+t,Toast.LENGTH_SHORT).show();
                    }
                });
        return jumlah;
    }

    public void cekFavoriteLokal(int id_user, int id_data_jasa){
        int jumlah = mydb.checkFavorite(id_user,id_data_jasa);
        if(jumlah>0){
            img_add_to_favorite.setImageResource(R.drawable.ic_check_blue);
        }else {
            img_add_to_favorite.setImageResource(R.drawable.ic_add_black_24dp);
        }
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean status = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return status;
    }

    public void addToFavorite(int id_user,int id_data_jasa, String user_token){
        btn_add_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jumlah_favorite =cekFavorite(id_user,id_data_jasa,user_token);
                if (jumlah_favorite==0){
                    serviceAddToFavorite(id_user,id_data_jasa,user_token);
                }
            }
        });
    }

    public void serviceAddToFavorite(int id_user, int id_data_jasa,String user_token){
        service.addToFavorite(id_user,id_data_jasa,user_token)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Ditambahkan ke Favorite",Toast.LENGTH_SHORT).show();
                            cekFavorite(id_user,id_data_jasa,user_token);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
