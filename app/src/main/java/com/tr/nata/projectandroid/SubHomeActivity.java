package com.tr.nata.projectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Adapter.listUserAdapter;
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

    public TextView tv_nama_kategori,tv_pesan;
    private RecyclerView recyclerView;
    private listUserAdapter listUserAdapter;

    private List<DataJasaItem> dataJasaItems = new ArrayList<>();
    private List<DataUserItem> dataUserItems=new ArrayList<>();

    ApiService service;
    ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_home);

        tv_nama_kategori=(TextView)findViewById(R.id.tv_nama_kategori);
        tv_pesan=(TextView)findViewById(R.id.tv_pesan);

        Bundle bundle = getIntent().getExtras();
        int id_kategori = bundle.getInt("id_kategori");
        tv_nama_kategori.setText(bundle.getString("namaKategori"));

        service=ApiClient.getApiService();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_list_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        service.showDataJasaByKategori(id_kategori)
                .enqueue(new Callback<ResponseDataJasa>() {
                    @Override
                    public void onResponse(Call<ResponseDataJasa> call, Response<ResponseDataJasa> response) {
                        if (response.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "success anjing", Toast.LENGTH_SHORT).show();
                            if (response.body().getDataJasa().size() > 0) {
                                Toast.makeText(getApplicationContext(), "jumlah " + response.body().getDataJasa().size(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "jumlah user " + response.body().getDataUser().size(), Toast.LENGTH_SHORT).show();
                                dataJasaItems = response.body().getDataJasa();
                                dataUserItems = response.body().getDataUser();
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
                        tv_pesan.setText("error : "+t);
                    }
                });
//                .enqueue(new Callback<List<ResponseDataJasa>>() {
//                    @Override
//                    public void onResponse(Call<List<ResponseDataJasa>> call, Response<List<ResponseDataJasa>> response) {
//                        if (response.isSuccessful()){
//
////                            dataJasaItems=response.body().getDataJasa();
////                            dataJasaItems=response.body();
////                            dataUserItems=response.body();
//                            Toast.makeText(getApplicationContext(),"success anjing",Toast.LENGTH_SHORT).show();
//                            if (response.body().size()>0){
////                                dataJasaItems=response.body();
//                                Toast.makeText(getApplicationContext(),"jumlah "+response.body().size(),Toast.LENGTH_SHORT).show();
//                            }else {
//                                tv_pesan.setText("Data kosong");
//                            }
//
//                        }else {
//                            Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ResponseDataJasa>> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),"eror : "+t,Toast.LENGTH_SHORT).show();
//                        tv_pesan.setText("error : "+t);
//                    }
//                });
//                .enqueue(new Callback<List<ResponseDataJasa>>() {
//                    @Override
//                    public void onResponse(Call<List<ResponseDataJasa>> call, Response<List<ResponseDataJasa>> response) {
//                        if (response.isSuccessful()){
//                            if (response.body().size()>0){
//                                dataJasaItems=response.body().getDataJasa();
//                                dataUserItems=response.body();
//                                Toast.makeText(getApplicationContext(),"success anjing",Toast.LENGTH_SHORT).show();
////                            listUserAdapter.notifyDataSetChanged();
//                                setAdapter();
//                            }else {
//                                tv_pesan.setText("Data kosong");
//
//                            }
//
//                        }else {
//                            Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ResponseDataJasa>> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),"eror : "+t,Toast.LENGTH_SHORT).show();
//                    }
//                });

    }
    public void setAdapter(){
//        listUserAdapter = new listUserAdapter(dataJasaItems,dataUserItems,this);
        listUserAdapter = new listUserAdapter(dataJasaItems,dataUserItems,this);
        recyclerView.setAdapter(listUserAdapter);

    }
}
