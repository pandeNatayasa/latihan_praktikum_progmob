package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.tr.nata.projectandroid.Adapter.kategoriAdapter;
import com.tr.nata.projectandroid.Adapter.kategoriAdminAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseKategori;
import com.tr.nata.projectandroid.model.ResponseStoreKategori;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdminActivity extends AppCompatActivity {

    FloatingActionButton fab_add_kategori;
//    Button btn_toAddKategori;
    ImageView btn_logout;
    ApiService service,serviceAddKategori;
    private List<DataKategoriItem> dataKategoriItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private kategoriAdminAdapter adapter;
    AlertDialog.Builder dialog;
//    LayoutInflater inflater_add_new;
//    View dialogView;
//    EditText et_nama_kategori, et_logo_kategori;
    String user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        btn_logout=findViewById(R.id.btn_logout);
//        btn_toAddKategori=findViewById(R.id.btn_toAddKategori);
        recyclerView=findViewById(R.id.recyclerview_list_kategori_inHomeAdmin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab_add_kategori=findViewById(R.id.fab_add_kategori);

        service=ApiClient.getApiService();
        serviceAddKategori=ApiClient.getApiService();

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(HomeAdminActivity.this)
                        .setTitle("Really Logout")
                        .setMessage("Are you sure want to logout ?")
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                                sharedPref.edit().clear().commit();

                                Intent intent = new Intent(HomeAdminActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }).create().show();
            }
        });

//        btn_toAddKategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent toAddKategori = new Intent(HomeAdminActivity.this,AddKategoriAdminActivity.class);
//                startActivity(toAddKategori);
//            }
//        });

        fab_add_kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeAdminActivity.this,"Add Job",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeAdminActivity.this,AddKategoriAdminActivity.class);
                startActivity(intent);
//                Toast.makeText(getActivity().getApplicationContext(),"Add Job",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity().getApplicationContext(),AddJobActivity.class);
//                startActivity(intent);


//                dialog= new AlertDialog.Builder(view.getContext());
//                inflater_add_new= getLayoutInflater();//(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                dialogView = inflater_add_new.inflate(R.layout.activity_add_kategory_admin,null);
//                dialog.setView(dialogView);
//                dialog.setCancelable(true);
//                dialog.setIcon(R.mipmap.ic_launcher);
//                dialog.setTitle("New Kategori");
//
////                Button btn_new_job=dialogView.findViewById(R.id.btn_add_new_job);
//                et_nama_kategori=dialogView.findViewById(R.id.et_addkategori);
//                et_logo_kategori=dialogView.findViewById(R.id.et_logo_kategori);
//
//                //membersihkan edit text
//                et_nama_kategori.setText("");
//                et_logo_kategori.setText("");
//
//
//                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
////                        callApiAddNew();
//                        String kategori = et_nama_kategori.getText().toString();
//                        String logo_kategori = et_logo_kategori.getText().toString();
//
//                        callApiAddKategori(kategori,logo_kategori);
//
//                    }
//                });
//
//                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                dialog.show();

            }
        });

        callApi();

    }

    private void callApi(){
        service.getKategori(user_token)
                .enqueue(new Callback<ResponseKategori>() {
                    @Override
                    public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                        if (response.isSuccessful()){

                            dataKategoriItems=response.body().getDataKategori();

                            setAdapter();

                        }else {
                            Toast.makeText(getApplicationContext(),"login gagal",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKategori> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"login gagal koneksi",Toast.LENGTH_SHORT).show();

                    }
                });
    }

//    private void callApiAddKategori(String kategori, String logo_kategori){
//        serviceAddKategori.addKategori(kategori,logo_kategori,user_token)
//                .enqueue(new Callback<ResponseStoreKategori>() {
//                    @Override
//                    public void onResponse(Call<ResponseStoreKategori> call, Response<ResponseStoreKategori> response) {
//                        if (response.isSuccessful()){
//                            Toast.makeText(getApplicationContext(),"sukses menambahkan kategori baru",Toast.LENGTH_SHORT).show();
//                            dataKategoriItems=response.body().getDataKategori();
//                            setAdapter();
//                        }else {
//                            Toast.makeText(getApplicationContext(),"login gagal",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseStoreKategori> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),"error"+t,Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    private void setAdapter(){
        adapter = new kategoriAdminAdapter(this,dataKategoriItems);
        recyclerView.setAdapter(adapter);
    }
}
