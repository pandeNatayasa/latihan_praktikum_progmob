package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tr.nata.projectandroid.AdminListFreeLanceActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseStoreKategori;
import com.tr.nata.projectandroid.updateKategoriAdminActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kategoriAdminAdapter extends RecyclerView.Adapter<kategoriAdminAdapter.ViewHolder> {

    private List<DataKategoriItem> dataKategoriItems;
    private Context context;
    private CardView cardListKategoriAdmin;
    ImageView img_edit, img_delete,img_logo_kategori;
    ApiService serviceDeleteKaegori,serviceEditKategori;
    View dialogView;
    AlertDialog.Builder dialog;
    LayoutInflater inflater_add_new;
    EditText et_nama_kategori, et_logo_kategori;
    String user_token, namaKategori;


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nama_kategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            serviceDeleteKaegori=ApiClient.getApiService();
            serviceEditKategori=ApiClient.getApiService();

            tv_nama_kategori=itemView.findViewById(R.id.tv_data_kategori_inHomeAdmin);
            cardListKategoriAdmin = itemView.findViewById(R.id.cardView_data_kategori_inHomeAdmin);
            img_delete=itemView.findViewById(R.id.btn_delete_data_kategori_inAdmin);
            img_edit=itemView.findViewById(R.id.btn_edit_data_kategori_inAdmin);
            img_logo_kategori=itemView.findViewById(R.id.img_data_kategori_user);

            SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
            user_token = sharedPref.getString("user_token","");

            cardListKategoriAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();

                    Intent intent = new Intent(context,AdminListFreeLanceActivity.class);
                    namaKategori = dataKategoriItem.getKategori();
                    int id = dataKategoriItem.getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("namaKategori", namaKategori);
                    bundle.putInt("id_kategori",id);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();
                    namaKategori = dataKategoriItem.getKategori();

                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Really Delete")
                            .setMessage("Are you sure want to delete kategori "+namaKategori+" ?")
                            .setNegativeButton(android.R.string.no,null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(itemView.getContext(),"will be delete soon",Toast.LENGTH_SHORT).show();

                                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();
                                    int id = dataKategoriItem.getId();

                                    serviceDeleteKaegori.deleteKategori(id,user_token)
                                            .enqueue(new Callback<ResponseStoreKategori>() {
                                                @Override
                                                public void onResponse(Call<ResponseStoreKategori> call, Response<ResponseStoreKategori> response) {
                                                    if (response.isSuccessful()){
                                                        if (response.body().isStatus()){
                                                            Toast.makeText(itemView.getContext(),"delete success",Toast.LENGTH_SHORT).show();
                                                            dialogInterface.dismiss();
                                                            dataKategoriItems.remove(dataKategoriItem);
                                                            notifyDataSetChanged();
                                                        }else {
                                                            Toast.makeText(itemView.getContext(),"delete gagal",Toast.LENGTH_SHORT).show();
                                                        }

                                                    }else {
                                                        Toast.makeText(itemView.getContext(),"something wrong",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ResponseStoreKategori> call, Throwable t) {
                                                    Toast.makeText(itemView.getContext(),"error "+t,Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }).create().show();
                }
            });

            img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();

                    Intent intent = new Intent(context,updateKategoriAdminActivity.class);
                    namaKategori = dataKategoriItem.getKategori();
                    int id = dataKategoriItem.getId();
                    String logo_kategori = dataKategoriItem.getLogoKategori();
                    Bundle bundle = new Bundle();
                    bundle.putString("namaKategori", namaKategori);
                    bundle.putString("logoKategori", logo_kategori);
                    bundle.putInt("idKategori",id);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    public kategoriAdminAdapter(Context context,List<DataKategoriItem> dataKategoriItems){
        this.dataKategoriItems=dataKategoriItems;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_data_kategori_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
//        Class<? extends List> dataKategoriItem = responseKategori.getClass();
//
        holder.itemView.setTag(dataKategoriItems.get(position));
//
//        holder.tv_kategori_1.setText(dataKategoriItem);
        Log.d("position","ke-"+position);
        DataKategoriItem dataKategoriItem = dataKategoriItems.get(position);
        holder.tv_nama_kategori.setText(dataKategoriItem.getKategori());
        String url = "http://172.17.100.2:8000"+dataKategoriItem.getLogoKategori();
        Glide.with(holder.itemView).load(url).into(img_logo_kategori);
    }

    @Override
    public int getItemCount(){
        return dataKategoriItems.size();
    }


}
