package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.tr.nata.projectandroid.Adapter.ListDataJasaInUserAdapter;
import com.tr.nata.projectandroid.Adapter.dataJasaAdminAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasa;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminListFreeLanceActivity extends AppCompatActivity {

//    FloatingActionButton fab_add_job;
    private RecyclerView recyclerView;
    private Bundle bundle;
    private dataJasaAdminAdapter dataJasaAdapter;
    DatabaseHelper myDb;
    TextView tv_pesan,tv_nama_kategori;
    String user_token;

    AlertDialog.Builder dialog;
    LayoutInflater inflater_add_new;
    View dialogView;
    EditText et_id_kategori,et_pekerjaan,et_estimasi_gaji,et_pengalaman_kerja,
            et_usia,et_no_telp,et_email,et_status,et_alamat;

    private List<DataJasaItem> dataJasaItems = new ArrayList<>();
    private List<DataUserItem> dataUserItems=new ArrayList<>();

    ApiService service,service_add_new;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inadmin_list_frelance);

        service=ApiClient.getApiService();
        service_add_new=ApiClient.getApiService();

        bundle = getIntent().getExtras();
        String nama_kategori = bundle.getString("namaKategori");
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

        recyclerView=findViewById(R.id.recyclerview_data_jasa_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        fab_add_job=findViewById(R.id.fab_add_job);
        tv_pesan  = findViewById(R.id.tv_pesan_inSubHomeAdmin);
//        tv_nama_kategori=findViewById(R.id.tv_nama_kategori_inSubHomeAdmin);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_sub_home_admin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(nama_kategori);

//        tv_nama_kategori.setText(nama_kategori);

//        fab_add_job.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(getActivity().getApplicationContext(),"Add Job",Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(getActivity().getApplicationContext(),AddJobActivity.class);
////                startActivity(intent);
//
//
//                dialog= new AlertDialog.Builder(view.getContext());
//                inflater_add_new= getLayoutInflater();//(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                dialogView = inflater_add_new.inflate(R.layout.activity_add_job,null);
//                dialog.setView(dialogView);
//                dialog.setCancelable(true);
//                dialog.setIcon(R.mipmap.ic_launcher);
//                dialog.setTitle("New Data Jasa");
//
////                Button btn_new_job=dialogView.findViewById(R.id.btn_add_new_job);
//                et_id_kategori=dialogView.findViewById(R.id.et_kategori_new);
//                et_pekerjaan=dialogView.findViewById(R.id.et_pekerjaan_new);
//                et_estimasi_gaji=dialogView.findViewById(R.id.et_estimasi_gaji_new);
//                et_pengalaman_kerja=dialogView.findViewById(R.id.et_pengalaman_kerja_new);
//                et_usia=dialogView.findViewById(R.id.et_usia_new);
//                et_no_telp=dialogView.findViewById(R.id.et_no_telp_new);
//                et_email=dialogView.findViewById(R.id.et_email_new);
//                et_status=dialogView.findViewById(R.id.et_status_new);
//                et_alamat=dialogView.findViewById(R.id.et_alamat_new);
//
//                //membersihkan edit text
//                et_id_kategori.setText("");
//                et_pekerjaan.setText("");
//                et_estimasi_gaji.setText("");
//                et_pengalaman_kerja.setText("");
//                et_usia.setText("");
//                et_no_telp.setText(null);
//                et_email.setText(null);
//                et_status.setText(null);
//                et_alamat.setText(null);
//
//                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        callApiAddNew();
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
//
//            }
//        });

        callApi();
    }


    private void callApi(){
        int id_kategori = bundle.getInt("id_kategori");
        Toast.makeText(this,"id kategori :"+String.valueOf(id_kategori),Toast.LENGTH_SHORT).show();
        service.showDataJasaByKategoriForAdmin(id_kategori,user_token)
                .enqueue(new Callback<ResponseDataJasa>() {
                    @Override
                    public void onResponse(Call<ResponseDataJasa> call, Response<ResponseDataJasa> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "success beb admin", Toast.LENGTH_SHORT).show();
                            if (response.body().getDataJasa().size() > 0) {
                                dataJasaItems = response.body().getDataJasa();
                                dataUserItems = response.body().getDataUser();
                                setAdapter();

                            }else {
                                tv_pesan.setText(" Data Kosong ");
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "something error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDataJasa> call, Throwable t) {

                    }
                });
    }

    private void setAdapter(){
        dataJasaAdapter = new dataJasaAdminAdapter(this,dataJasaItems,dataUserItems);
        recyclerView.setAdapter(dataJasaAdapter);
    }

    private void callApiAddNew(){
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);

        int id_user = sharedPref.getInt("id_user_login",0);
        int id_kategori = Integer.parseInt(String.valueOf(et_id_kategori.getText()));
        String pekerjaan = et_pekerjaan.getText().toString();
        int estimasi_gaji = Integer.parseInt(String.valueOf(et_estimasi_gaji.getText()));
        String pengalaman_kerja = et_pengalaman_kerja.getText().toString();
        int usia= Integer.parseInt(String.valueOf(et_usia.getText()));
        String no_telp = et_no_telp.getText().toString();
        String email = et_email.getText().toString();
        String status = et_status.getText().toString();
        String alamat = et_alamat.getText().toString();

//        Toast.makeText(AddJobActivity.this,"Haiiii",Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"id_kategori "+id_kategori,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"id_user"+id_user,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"pekerjaan :"+pekerjaan,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"estimasi gaji :"+estimasi_gaji,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"pengalaman kerja :"+pengalaman_kerja,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"no telp :"+no_telp,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"email :"+email,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"status :"+status,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"alamat :"+alamat,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"id kecamatan :"+id_kecamatan,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"usia :"+usia,Toast.LENGTH_SHORT).show();
        service.newDataJasaUser(id_kategori,id_user,pekerjaan,estimasi_gaji,pengalaman_kerja,usia,no_telp,email,status,alamat,user_token)
                .enqueue(new Callback<com.tr.nata.projectandroid.model.Response>() {
                    @Override
                    public void onResponse(Call<com.tr.nata.projectandroid.model.Response> call, retrofit2.Response<com.tr.nata.projectandroid.model.Response> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data Jasa Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                            callApi();
//                            Intent intent = new Intent(AddJobActivity.this,HomeActivity.class);
//                            startActivity(intent);
//                            Fragment fragment = new FragmentListFrelance();
//                            getSupportFragmentManager().beginTransaction().add(R.id.frag_layout,fragment).addToBackStack(FragmentListFrelance.class.getSimpleName()).commit();
                        }else {
                            Toast.makeText(getApplicationContext(),"Data Jasa Gagal Disimpan",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.tr.nata.projectandroid.model.Response> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error : "+t,Toast.LENGTH_SHORT).show();
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
