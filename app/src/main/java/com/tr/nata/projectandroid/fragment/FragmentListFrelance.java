package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Adapter.dataJasaUserAdapter;
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

    private RecyclerView recyclerView;
    private dataJasaUserAdapter adapter;
    DatabaseHelper myDb;

    private List<ResponseDataJasaUser> dataJasaUsers = new ArrayList<>();

    ApiService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_frelance,container,false);

//        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        Integer id_user_login = Integer.valueOf(sharedPref.getString("id_user_login",""));

        tv_error=view.findViewById(R.id.tv_error_here);

        service=ApiClient.getApiService();

        recyclerView=view.findViewById(R.id.recyclerview_data_jasa_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        callApi();
        return view;
    }

    private void callApi(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        Integer id_user_login = Integer.parseInt(sharedPref.getString("id_user_login",""));
        Integer id_user_login = sharedPref.getInt("id_user_login",0);

        service.showDataJasaUser(id_user_login)
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
        adapter=new dataJasaUserAdapter(getActivity(),dataJasaUsers);
        recyclerView.setAdapter(adapter);
    }
}
