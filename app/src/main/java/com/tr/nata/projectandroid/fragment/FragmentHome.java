package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Adapter.kategoriAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.HomeActivity;
import com.tr.nata.projectandroid.MainActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.TryPerofilleActivity;
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

    TextView tv_namaUser,tv_home_to_favorite;

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private kategoriAdapter adapter;
    DatabaseHelper myDb;
    ImageView home_to_profille,img_home_to_favorite;
    private List<ResponseKategori> responseKategoris = new ArrayList<>();

    private List<DataKategoriItem> dataKategoriItems = new ArrayList<>();

    ApiService service;
    String user_token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);

//        tv_namaUser = view.findViewById(R.id.tv_nama);
        home_to_profille=view.findViewById(R.id.img_profille_home_to_profille);
        toolbar=view.findViewById(R.id.toolbarid);
        tv_home_to_favorite=view.findViewById(R.id.tv_home_to_favorite);
        img_home_to_favorite=view.findViewById(R.id.img_home_to_favorite);

        service=ApiClient.getApiService();
        myDb=new DatabaseHelper(getActivity());

        SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

//        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        String nama_user_login = sharedPref.getString("nama_user_login","");
//        tv_namaUser.setText(nama_user_login);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_kategori);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(),2,GridLayoutManager.VERTICAL,false));

//        Button btn_logout = (Button) view.findViewById(R.id.btn_logout);
//        Button btn_viewKategoriLokal = view.findViewById(R.id.btn_viewKategoriLokal);

        home_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TryPerofilleActivity.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });

        img_home_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentFavorite ff = new FragmentFavorite();
//                BottomNavigationView bottomNavView = (BottomNavigationView)view.findViewById(R.id.btn_nav_menu);
//                bottomNavView.setSelectedItemId(R.id.nav_favorite);
//                View view1=bottomNavView.findViewById(R.id.nav_favorite);
//                view1.performClick();
                ft.replace(R.id.frag_layout,ff);
                ft.commit();
            }
        });

        tv_home_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentFavorite ff = new FragmentFavorite();
//                BottomNavigationView bottomNavView = (BottomNavigationView)view.findViewById(R.id.btn_nav_menu);
//                View view1=bottomNavView.findViewById(R.id.nav_favorite);
//                view1.performClick();
                ft.replace(R.id.frag_layout,ff);
                ft.commit();
            }
        });

//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Boolean login = false;
//                SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putBoolean("status_login",login);
//                editor.putString("status_login_string", String.valueOf(login));
//                editor.apply();
//
//                Intent intent = new Intent(getActivity(),MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });
//
//        btn_viewKategoriLokal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor res = myDb.getAllData();
//                if (res.getCount()==0){
//                    //show message
//                    showMessage("Eror","Nothing Found");
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()){
//                    buffer.append("Id : "+res.getString(0)+"\n");
//                    buffer.append("Kategori : "+res.getString(1)+"\n");
//                }
//                //show all data
//                showMessage("Data",buffer.toString());
//            }
//        });

        callKategoriLokal();
        callApi();

        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // Do something that differs the Activity's menu here
//        super.onCreateOptionsMenu(menu, inflater);
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {

//            case R.id:
//
//                // Do Fragment menu item stuff here
//                return true;
//
//            default:
//                break;
//        }
//
//        return false;
//    }

    private void callApi(){
        service.getKategori(user_token)
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
                        Toast.makeText(getActivity().getApplicationContext(),"Anda Sedang Ofline",Toast.LENGTH_SHORT).show();

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
