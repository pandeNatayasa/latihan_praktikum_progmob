package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Adapter.kategoriAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.HomeActivity;
import com.tr.nata.projectandroid.MainActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseKategori;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    TextView tv_namaUser;

    private RecyclerView recyclerView;
    private kategoriAdapter adapter;
    DatabaseHelper myDb;

    private List<ResponseKategori> responseKategoris = new ArrayList<>();

    private List<DataKategoriItem> dataKategoriItems = new ArrayList<>();

    ApiService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        tv_namaUser = view.findViewById(R.id.tv_nama);

        service=ApiClient.getApiService();
        myDb=new DatabaseHelper(getActivity());

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        tv_namaUser.setText(nama_user_login);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_kategori);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        Button btn_logout = (Button) view.findViewById(R.id.btn_logout);
        Button btn_viewKategoriLokal = view.findViewById(R.id.btn_viewKategoriLokal);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean login = false;
                SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("status_login",login);
                editor.putString("status_login_string", String.valueOf(login));
                editor.apply();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        btn_viewKategoriLokal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount()==0){
                    //show message
                    showMessage("Eror","Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : "+res.getString(0)+"\n");
                    buffer.append("Kategori : "+res.getString(1)+"\n");
                }
                //show all data
                showMessage("Data",buffer.toString());
            }
        });

        callKategoriLokal();
        callApi();

        return view;
    }


    private void callApi(){
        service.getKategori()
                .enqueue(new Callback<ResponseKategori>() {
                    @Override
                    public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                        if (response.isSuccessful()){
                            DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

                            dbHelper.deleteKategori();

                            dataKategoriItems=response.body().getDataKategori();

                            for (DataKategoriItem dataKategoriItem:dataKategoriItems){
                                dbHelper.insertDataKategori(dataKategoriItem.getId(),dataKategoriItem.getKategori());
                            }
                            setAdapter();
//                            recyclerView.notifyAll();

//                            responseKategoris.addAll((Collection<? extends ResponseKategori>) response.body());

//                            adapter=new kategoriAdapter(responseKategoris,this);
//                            recyclerView.setAdapter(adapter);
//                            dataKategoriItems = response.body().getDataKategori();
//                            adapter = new kategoriAdapter(dataKategoriItems,this);
//                            recyclerView.setAdapter(adapter);
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"login gagal",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKategori> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(),"login gagal koneksi",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void setAdapter(){
        adapter = new kategoriAdapter(getActivity(),dataKategoriItems);
        recyclerView.setAdapter(adapter);
    }

    private void callKategoriLokal(){
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        dataKategoriItems=dbHelper.selectKategori();
        setAdapter();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
