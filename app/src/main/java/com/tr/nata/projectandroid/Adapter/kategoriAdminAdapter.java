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

import com.tr.nata.projectandroid.AdminListFreeLanceActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseStoreKategori;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kategoriAdminAdapter extends RecyclerView.Adapter<kategoriAdminAdapter.ViewHolder> {

    private List<DataKategoriItem> dataKategoriItems;
    private Context context;
    private CardView cardListKategoriAdmin;
    ImageView img_edit, img_delete;
    ApiService serviceDeleteKaegori,serviceEditKategori;
    View dialogView;
    AlertDialog.Builder dialog;
    LayoutInflater inflater_add_new;
    EditText et_nama_kategori, et_logo_kategori;
    String user_token;


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

            SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
            user_token = sharedPref.getString("user_token","");

            cardListKategoriAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();

                    Intent intent = new Intent(context,AdminListFreeLanceActivity.class);
                    String namaKategori = dataKategoriItem.getKategori();
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
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Really Delete")
                            .setMessage("Are you sure want to delete ?")
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
                    dialog= new AlertDialog.Builder(itemView.getContext());
                    inflater_add_new= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    dialogView = inflater_add_new.inflate(R.layout.activity_add_kategory_admin,null);
                    dialog.setView(dialogView);
                    dialog.setCancelable(true);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("Update Kategori");

//                Button btn_new_job=dialogView.findViewById(R.id.btn_add_new_job);
                    et_nama_kategori=dialogView.findViewById(R.id.et_addkategori);
                    et_logo_kategori=dialogView.findViewById(R.id.et_logo_kategori);

                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();
                    String namaKategori = dataKategoriItem.getKategori();
                    String logoKategori = dataKategoriItem.getLogoKategori().toString();
                    int id_kategori = dataKategoriItem.getId();

                    //menseting isi edit text
                    et_nama_kategori.setText(namaKategori);
                    et_logo_kategori.setText(logoKategori);


                    dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                        callApiAddNew();
                            String kategori = et_nama_kategori.getText().toString();
                            String logo_kategori = et_logo_kategori.getText().toString();

                            serviceEditKategori.updateKategori(id_kategori,kategori,logo_kategori,user_token)
                                    .enqueue(new Callback<ResponseStoreKategori>() {
                                        @Override
                                        public void onResponse(Call<ResponseStoreKategori> call, Response<ResponseStoreKategori> response) {
                                            if (response.isSuccessful()){
                                                if (response.body().isStatus()){
                                                    Toast.makeText(itemView.getContext(),"update success",Toast.LENGTH_SHORT).show();
                                                    dialogInterface.dismiss();
                                                }else {
                                                    Toast.makeText(itemView.getContext(),"update gagal",Toast.LENGTH_SHORT).show();
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
//                            callApiAddKategori(kategori,logo_kategori);

                        }
                    });

                    dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    dialog.show();
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


    }

    @Override
    public int getItemCount(){
        return dataKategoriItems.size();
    }


}
