package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.tr.nata.projectandroid.Adapter.listUserAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubHomeActivity extends AppCompatActivity {

    public TextView tv_pesan;
    private RecyclerView recyclerView;
    private listUserAdapter listUserAdapter;
    private Bundle bundle;
    private Button btn_view_jasa;
    ImageView img_subHome_to_profille;
    String user_token;

    private List<DataJasaItem> dataJasaItems = new ArrayList<>();
    private List<DataUserItem> dataUserItems=new ArrayList<>();

    ApiService service;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_home);

        tv_pesan=(TextView)findViewById(R.id.tv_pesan);
//        btn_view_jasa=findViewById(R.id.btn_data_jasa);
        img_subHome_to_profille=findViewById(R.id.img_sub_home_to_profille);

        bundle = getIntent().getExtras();
        String nama_kategori = bundle.getString("namaKategori");
        service=ApiClient.getApiService();
        myDb=new DatabaseHelper(this);
//        ViewCompat.setTransitionName(findViewById(R.id.app_bar_sub_home),);
        CollapsingToolbarLayout  collapsingToolbarLayout= (CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbar_subhome);
        collapsingToolbarLayout.setTitle(nama_kategori);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid_sub_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_list_user);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

//        btn_view_jasa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor res = myDb.getAllDataJasa();
//                if (res.getCount()==0){
//                    //show message
//                    showMessage("Eror","Nothing Found");
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()){
//                    buffer.append("Nama : "+res.getString(4)+"\n");
//                    buffer.append("Pekerjaan : "+res.getString(5)+"\n");
//                }
//                //show all data
//                showMessage("Data",buffer.toString());
//            }
//        });

        img_subHome_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TryPerofilleActivity.class);
                startActivity(intent);
            }
        });

//        callDataLokal();
        callApi();
    }

    private void callApi(){
        int id_kategori = bundle.getInt("id_kategori");
        service.showDataJasaByKategori(id_kategori,user_token)
                .enqueue(new Callback<ResponseDataJasa>() {
                    @Override
                    public void onResponse(Call<ResponseDataJasa> call, Response<ResponseDataJasa> response) {
                        if (response.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "success beb", Toast.LENGTH_SHORT).show();
                            if (response.body().getDataJasa().size() > 0) {
                                Toast.makeText(getApplicationContext(), "jumlah " + response.body().getDataJasa().size(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "jumlah user " + response.body().getDataUser().size(), Toast.LENGTH_SHORT).show();

                                myDb.deleteJasa(id_kategori);

                                dataJasaItems = response.body().getDataJasa();
                                dataUserItems = response.body().getDataUser();

                                for (DataUserItem dataUserItem:dataUserItems){
                                    myDb.deleteUser(dataUserItem.getId());
                                }
                                for (DataUserItem dataUserItem:dataUserItems){
                                    myDb.insertDataUser(dataUserItem.getId(),dataUserItem.getName(),dataUserItem.getEmail(),dataUserItem.getJenisKelamin(),
                                            dataUserItem.getNoTelp(),dataUserItem.getTanggalLahir());
                                }
                                for (DataJasaItem dataJasaItem:dataJasaItems){
                                    myDb.insertDataJasa(dataJasaItem.getId(),dataJasaItem.getIdKategori(),dataJasaItem.getIdUser(),
                                            dataJasaItem.getPekerjaan(),dataJasaItem.getUsia(),dataJasaItem.getNoTelp(),dataJasaItem.getEmail(),
                                            dataJasaItem.getStatus(),dataJasaItem.getAlamat());
                                }
                                setAdapter();
                            } else {
                                tv_pesan.setText("Data kosong");
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDataJasa> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"eror : "+t,Toast.LENGTH_SHORT).show();
//                        tv_pesan.setText("error : "+t);
                    }
                });
    }
    private void setAdapter(){
//        listUserAdapter = new listUserAdapter(dataJasaItems,dataUserItems,this);
        listUserAdapter = new listUserAdapter(dataJasaItems,dataUserItems,this);
        recyclerView.setAdapter(listUserAdapter);

    }
    private void callDataLokal(){
        int id_kategori = bundle.getInt("id_kategori");
        dataJasaItems=myDb.selectDatajasa(id_kategori);
        for (DataJasaItem dataJasaItem:dataJasaItems){
            Toast.makeText(getApplicationContext(),"pekerjaan : "+dataJasaItem.getPekerjaan(),Toast.LENGTH_SHORT).show();
        }

        for (DataJasaItem dataJasaItem:dataJasaItems){
            Cursor curDataUser = myDb.getDataUser(dataJasaItem.getIdUser());
            int count = curDataUser.getCount();
            if (count>0){
                while (curDataUser.moveToNext()){
                    int id = curDataUser.getInt(curDataUser.getColumnIndex(DataUserItem.Entry.COLUMN_ID));
                    String name = curDataUser.getString(curDataUser.getColumnIndex(DataUserItem.Entry.COLUMN_NAME_USER));
                    String email = curDataUser.getString(curDataUser.getColumnIndex(DataUserItem.Entry.COLUMN_EMAIL_USER));
                    String jk = curDataUser.getString(curDataUser.getColumnIndex(DataUserItem.Entry.COLUMN_JK_USER));
                    String no_telp = curDataUser.getString(curDataUser.getColumnIndex(DataUserItem.Entry.COLUMN_NO_TELP_USER));
                    String tanggal_lahir = curDataUser.getString(curDataUser.getColumnIndex(DataUserItem.Entry.COLUMN_TANGGAL_LAHIR_USER));

                    DataUserItem temp = new DataUserItem();
                    temp.setId(id);
                    temp.setName(name);
                    temp.setEmail(email);
                    temp.setJenisKelamin(jk);
                    temp.setNoTelp(no_telp);
                    temp.setTanggalLahir(tanggal_lahir);
                    dataUserItems.add(temp);
                }
            }
            curDataUser.close();
        }
        setAdapter();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
