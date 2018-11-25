package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.TryPerofilleActivity;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class FragmentNewJob extends Fragment {

    TextView tv_error;
    ImageView img_add_to_profille;

    EditText et_id_kategori,et_pekerjaan,et_estimasi_gaji,et_pengalaman_kerja,
            et_usia,et_no_telp,et_email,et_status,et_alamat;

    ApiService service;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_job_by_user, container, false);

        tv_error=view.findViewById(R.id.tv_error_here);
        img_add_to_profille=view.findViewById(R.id.img_new_job_to_profille);

        service=ApiClient.getApiService();

        Button btn_new_job=view.findViewById(R.id.btn_save_new_job);
        et_id_kategori=view.findViewById(R.id.et_kategori_new);
        et_pekerjaan=view.findViewById(R.id.et_pekerjaan_new);
        et_estimasi_gaji=view.findViewById(R.id.et_estimasi_gaji_new);
        et_pengalaman_kerja=view.findViewById(R.id.et_pengalaman_kerja_new);
        et_usia=view.findViewById(R.id.et_usia_new);
        et_no_telp=view.findViewById(R.id.et_no_telp_new);
        et_email=view.findViewById(R.id.et_email_new);
        et_status=view.findViewById(R.id.et_status_new);
        et_alamat=view.findViewById(R.id.et_alamat_new);

        //membersihkan edit text
        et_id_kategori.setText("");
        et_pekerjaan.setText("");
        et_estimasi_gaji.setText("");
        et_pengalaman_kerja.setText("");
        et_usia.setText("");
        et_no_telp.setText(null);
        et_email.setText(null);
        et_status.setText(null);
        et_alamat.setText(null);

        btn_new_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiAddNew();
            }
        });

        img_add_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TryPerofilleActivity.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });


        return view;
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

        service.newDataJasaUser(id_kategori,id_user,pekerjaan,estimasi_gaji,pengalaman_kerja,usia,no_telp,email,status,alamat)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<com.tr.nata.projectandroid.model.Response> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getActivity().getApplicationContext(),"Data Jasa Berhasil Disimpan",Toast.LENGTH_SHORT).show();

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
