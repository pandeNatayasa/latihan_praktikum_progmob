package com.tr.nata.projectandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

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

    public TextView tv_nama_kategori,tv_pesan;
    private RecyclerView recyclerView;
    private listUserAdapter listUserAdapter;
    private Bundle bundle;

    private List<DataJasaItem> dataJasaItems = new ArrayList<>();
    private List<DataUserItem> dataUserItems=new ArrayList<>();

    ApiService service;
    DatabaseHelper myDb;
    ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_home);

        tv_nama_kategori=(TextView)findViewById(R.id.tv_nama_kategori);
        tv_pesan=(TextView)findViewById(R.id.tv_pesan);

        bundle = getIntent().getExtras();
        tv_nama_kategori.setText(bundle.getString("namaKategori"));

        service=ApiClient.getApiService();
        myDb=new DatabaseHelper(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_list_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        callDataLokal();
        callApi();
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

    private void callApi(){
        int id_kategori = bundle.getInt("id_kategori");
        service.showDataJasaByKategori(id_kategori)
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
                                            dataJasaItem.getStatus(),dataJasaItem.getAlamat(),dataJasaItem.getIdKecamatan());
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

}
