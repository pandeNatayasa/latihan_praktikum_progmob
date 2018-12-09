package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tr.nata.projectandroid.Adapter.favoriteAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.Helper.ViewPagerAdapter;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.TryPerofilleActivity;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.ResponseFavorite;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFavorite extends Fragment {

    private AppBarLayout appBarLayout;
    ImageView favorite_to_profille,img_profille_inFavorite;
    private RecyclerView recyclerView;
    private favoriteAdapter adapter;
    private List<ResponseFavorite> responseFavorites = new ArrayList<>();
    ApiService service;
    private TextView tv_nama_profille, tv_jumlah_favorite;
    String user_token;
    DatabaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_favorite, container, false);

        myDb=new DatabaseHelper(getActivity());

        favorite_to_profille = view.findViewById(R.id.img_favorite_to_profille);
        recyclerView=view.findViewById(R.id.recyclerview_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        service=ApiClient.getApiService();
        tv_nama_profille=view.findViewById(R.id.tv_nama_proflle_inFavorite);
        tv_jumlah_favorite=view.findViewById(R.id.jumlah_favorite);
        img_profille_inFavorite=view.findViewById(R.id.img_proille_infavorite);

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        String nama_foto_profille = sharedPref.getString("user_foto_profille","");

        String url = "http://172.17.100.2:8000"+nama_foto_profille;
        Glide.with(this).load(url).into(img_profille_inFavorite);
        tv_nama_profille.setText(nama_user_login);

        user_token = sharedPref.getString("user_token","");

        favorite_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TryPerofilleActivity.class);
                intent.putExtra("Fragment_id",0);
                startActivity(intent);
//                getActivity().finish();
            }
        });

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.fragment_list_favorite_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorPrimaryLight);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        callFavoriteLocal();
                        callApi();
                    }
                },3000);
            }
        });

        callFavoriteLocal();
        callApi();

        return view;
    }
    private void callApi(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        Integer id_user_login = Integer.parseInt(sharedPref.getString("id_user_login",""));
        Integer id_user_login = sharedPref.getInt("id_user_login", 0);
        Toast.makeText(getActivity().getApplicationContext(),"id user "+String.valueOf(id_user_login)+" token : "+user_token,Toast.LENGTH_SHORT).show();
        service.showFavorite(id_user_login,user_token)
                .enqueue(new Callback<List<ResponseFavorite>>() {
                    @Override
                    public void onResponse(Call<List<ResponseFavorite>> call, Response<List<ResponseFavorite>> response) {
                        if (response.isSuccessful()){
                            myDb.deleteFavorite();
                            responseFavorites = response.body();

                            for (ResponseFavorite responseFavorite:responseFavorites){
                                myDb.insertDataFavorite(responseFavorite.getId(),responseFavorite.getIdUser(),responseFavorite.getIdDataJasa());
                            }

                            int jumlah = responseFavorites.size();
                            tv_jumlah_favorite.setText(String.valueOf(jumlah));
                            setAdapter();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"something eror",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ResponseFavorite>> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext()," eror : "+t,Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setAdapter(){
        adapter = new favoriteAdapter(getActivity(),responseFavorites);
        recyclerView.setAdapter(adapter);
    }

    private void callFavoriteLocal(){
        responseFavorites = myDb.getAllDataFavorite();
        int jumlah = responseFavorites.size();
        tv_jumlah_favorite.setText(String.valueOf(jumlah));
        setAdapter();
    }
}
