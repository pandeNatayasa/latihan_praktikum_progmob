package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Adapter.ListDataJasaInUserAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentListFrelance extends Fragment {

    TextView tv_error;
//    FloatingActionButton fab_add_job;

    private RecyclerView recyclerView;
    private ListDataJasaInUserAdapter adapter;
    DatabaseHelper myDb;

//    String id_kategori,id_user,pekerjaan,estimasi_gaji,pengalaman_kerja,
//            usia,no_telp,email,status,alamat,id_kecamatan;
    AlertDialog.Builder dialog;
    LayoutInflater inflater_add_new;
    View dialogView;
    EditText et_id_kategori,et_pekerjaan,et_estimasi_gaji,et_pengalaman_kerja,
            et_usia,et_no_telp,et_email,et_status,et_alamat;


    private List<ResponseDataJasaUser> dataJasaUsers = new ArrayList<>();

    ApiService service,service_add_new;
    String user_token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_inadmin_list_frelance,container,false);

//        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        Integer id_user_login = Integer.valueOf(sharedPref.getString("id_user_login",""));



        service=ApiClient.getApiService();
        service_add_new=ApiClient.getApiService();

        recyclerView=view.findViewById(R.id.recyclerview_data_jasa_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        fab_add_job=view.findViewById(R.id.fab_add_job);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_home);
//        HomeActivity main = (HomeActivity)getActivity();
//        main.setSupportActionBar(toolbar);

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
////                btn_new_job.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        callApiAddNew();
////                    }
////                });
//
//                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
////                        Toast.makeText(itemView.getContext(),"will be update soon",Toast.LENGTH_SHORT).show();
//
//                        callApiAddNew();
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
//
//            }
//        });

        callApi();
        return view;
    }

//    public boolean onCreateOptionsMenu(Menu menu){
//        getActivity().getMenuInflater().inflate(R.menu.toolbar_menu,menu);
//        return true;
//    }

    private void callApi(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        Integer id_user_login = Integer.parseInt(sharedPref.getString("id_user_login",""));
        Integer id_user_login = sharedPref.getInt("id_user_login",0);

        service.showDataJasaUser(id_user_login,user_token)
                .enqueue(new Callback<List<ResponseDataJasaUser>>() {
                    @Override
                    public void onResponse(Call<List<ResponseDataJasaUser>> call, Response<List<ResponseDataJasaUser>> response) {
                        if (response.isSuccessful()){
                            dataJasaUsers= response.body();
                            setAdapter();

                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"suksess tapi gagal",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ResponseDataJasaUser>> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(),"gagal koneksi"+t,Toast.LENGTH_SHORT).show();
                        tv_error.setText("error : "+t);
                    }
                });
//        service.showDataJasaUser(id_user_login)
//                .enqueue(new Callback<ResponseDataJasaUser>() {
//                    @Override
//                    public void onResponse(Call<ResponseDataJasaUser> call, Response<ResponseDataJasaUser> response) {
//                        if (response.isSuccessful()){
//                            dataJasaUsers= response.body();
//                            setAdapter();
//
//                        }else {
//                            Toast.makeText(getActivity().getApplicationContext(),"suksess tapi gagal",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseDataJasaUser> call, Throwable t) {
//                        Toast.makeText(getActivity().getApplicationContext(),"gagal koneksi"+t,Toast.LENGTH_SHORT).show();
//                        tv_error.setText("error : "+t);
//                    }
//                });
    }

    private void setAdapter(){
        adapter=new ListDataJasaInUserAdapter(getActivity(),dataJasaUsers);
        recyclerView.setAdapter(adapter);
    }

    private void callApiAddNew(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);

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
                            Toast.makeText(getActivity().getApplicationContext(),"Data Jasa Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                            callApi();
//                            Intent intent = new Intent(AddJobActivity.this,HomeActivity.class);
//                            startActivity(intent);
//                            Fragment fragment = new FragmentListFrelance();
//                            getSupportFragmentManager().beginTransaction().add(R.id.frag_layout,fragment).addToBackStack(FragmentListFrelance.class.getSimpleName()).commit();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"Data Jasa Gagal Disimpan",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.tr.nata.projectandroid.model.Response> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(),"Error : "+t,Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
