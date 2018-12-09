package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.DetailUserInAdminActivity;
import com.tr.nata.projectandroid.HomeActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.fragment.FragmentFavorite;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;
import com.tr.nata.projectandroid.model.ResponseFavorite;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class favoriteAdapter extends RecyclerView.Adapter<favoriteAdapter.ViewHolder> {

    private Context context;
    private List<ResponseFavorite> responseFavorites;

    ImageView img_delete;
    DatabaseHelper mydb;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_data_user_inFavorite, tv_data_jasa_inFavorite;
        public CardView cardView_data_Favorite;
        public ApiService serviceDeleteFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            mydb = new DatabaseHelper(itemView.getContext());
            tv_data_user_inFavorite=itemView.findViewById(R.id.tv_data_user_inFavorite);
            tv_data_jasa_inFavorite=itemView.findViewById(R.id.tv_data_jasa_user_inFavorite);
            cardView_data_Favorite=itemView.findViewById(R.id.cardView_data_jasa_favorite);
            img_delete=itemView.findViewById(R.id.btn_delete_data_jasa_inFavorite);
            serviceDeleteFavorite=ApiClient.getApiService();

            SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
            String token = sharedPref.getString("user_token","");

            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ResponseFavorite responseFavorite = (ResponseFavorite) itemView.getTag();
                    int id_favorite =responseFavorite.getId();
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Really Delete")
                            .setMessage("Are you sure want to delete ?")
                            .setNegativeButton(android.R.string.no,null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(itemView.getContext(),"token : "+String.valueOf(id_favorite),Toast.LENGTH_SHORT).show();
                                    serviceDeleteFavorite.delete_favorite(id_favorite,token)
                                            .enqueue(new Callback<Response>() {
                                                @Override
                                                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                                    if (response.isSuccessful()){
//                                                        FragmentFavorite fragmentFavorite=new FragmentFavorite();
//                                                        Intent intent = new Intent(itemView.getContext(),HomeActivity.class);
//                                                        itemView.getContext().startActivity(intent);
//                                                        FragmentManager fm =itemView.getContext().getFragmentManager();
//                                                        FragmentTransaction ft = fm.beginTransaction();
//                                                        FragmentFavorite ff = new FragmentFavorite();
//                BottomNavigationView bottomNavView = (BottomNavigationView)view.findViewById(R.id.btn_nav_menu);
//                View view1=bottomNavView.findViewById(R.id.nav_favorite);
//                view1.performClick();
//                                                        ft.replace(R.id.frag_layout,ff);
//                                                        ft.commit();
                                                        Toast.makeText(itemView.getContext(),"sukses",Toast.LENGTH_SHORT).show();
                                                        responseFavorites.remove(responseFavorite);
                                                        notifyDataSetChanged();

                                                    }else {
                                                        Toast.makeText(itemView.getContext(),"sukses tapi gagal",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<Response> call, Throwable t) {
                                                    Toast.makeText(itemView.getContext(),"error "+t,Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }).create().show();
                }
            });

        }
    }

    public favoriteAdapter(Context context,List<ResponseFavorite> responseFavorites){
        this.responseFavorites=responseFavorites;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_data_favorite,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(responseFavorites.get(position));

        ResponseFavorite responseFavorite = responseFavorites.get(position);

        DataUserItem dataUser = mydb.selectDataUser(responseFavorite.getIdUser());
        DataJasaItem dataJasa = mydb.selectOneDatajasa(responseFavorite.getIdDataJasa());

        holder.tv_data_user_inFavorite.setText(dataUser.getName());
        holder.tv_data_jasa_inFavorite.setText(dataJasa.getPekerjaan());

        holder.cardView_data_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_data_jasa = dataJasa.getId();
                String nama = dataUser.getName();
                String jasa = dataJasa.getPekerjaan();
                String gaji = dataJasa.getEstimasi_gaji().toString();
                String usia = String.valueOf(dataJasa.getUsia());
                String tanggal_lahir = dataUser.getTanggalLahir();
                String no_telp = String.valueOf(dataJasa.getNoTelp()) ;
                String email = dataJasa.getEmail();
                String status = dataJasa.getStatus();
                String pendidikan = dataJasa.getPengalaman_kerja();
                String alamat = dataJasa.getAlamat();

                Intent intent = new Intent(context,DetailUserInAdminActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_data_jasa",id_data_jasa);
                bundle.putString("nama", nama);
                bundle.putString("jasa",jasa);
                bundle.putString("gaji",gaji);
                bundle.putString("usia",usia);
                bundle.putString("tanggal_lahir",tanggal_lahir);
                bundle.putString("no_telp",no_telp);
                bundle.putString("email",email);
                bundle.putString("status",status);
                bundle.putString("pendidikan",pendidikan);
                bundle.putString("alamat",alamat);

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount(){
        return responseFavorites.size();
    }

}
