package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.DetailUserActivity;
import com.tr.nata.projectandroid.DetailUserInAdminActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;

import java.util.List;

public class dataJasaAdminAdapter extends RecyclerView.Adapter<dataJasaAdminAdapter.ViewHolder> {

    private Context context;
    private List<DataJasaItem> dataJasaItems;
    private List<DataUserItem> dataUserItems;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nama_user, tv_pekerjaan;
        public ImageView btn_validasi, btn_hapus;
        public CardView card_data_jasa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            tv_nama_user=itemView.findViewById(R.id.tv_nama_user_inSubHomeAdmin);
            tv_pekerjaan=itemView.findViewById(R.id.tv_pekerjaan_inSubHomeAdmin);
            btn_validasi=itemView.findViewById(R.id.btn_validasi_inSubHomeAdmin);
            btn_hapus=itemView.findViewById(R.id.btn_delete_data_jasa_inSubHomeAdmin);
            card_data_jasa=itemView.findViewById(R.id.cardView_data_Jasa_inSubHomeAdmin);

        }
    }
    public dataJasaAdminAdapter(Context context,List<DataJasaItem> dataJasaItems,List<DataUserItem> dataUserItems){
        this.dataJasaItems =dataJasaItems;
        this.dataUserItems=dataUserItems;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_data_jasa_inadmin,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        holder.itemView.setTag(dataUserItems.get(position));
        holder.itemView.setTag(dataJasaItems.get(position));

        DataJasaItem dataJasa = dataJasaItems.get(position);
        DataUserItem dataUser = dataUserItems.get(position);

        holder.tv_nama_user.setText(dataUser.getName());
        holder.tv_pekerjaan.setText(dataJasa.getPekerjaan());

        Log.d("position","ke-"+position);
        holder.tv_pekerjaan.setText(dataJasa.getPekerjaan());
        holder.tv_nama_user.setText(dataUser.getName());

        holder.card_data_jasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                DataJasaItem dataJasaItem = (DataJasaItem) itemView.getTag();
//                DataUserItem dataUserItem = (DataUserItem) itemView.getTag();

                int id_data_jasa = dataJasa.getId();
                String nama = dataUser.getName();
//                    String nama ="aa";
                String jasa = dataJasa.getPekerjaan();
                String gaji = dataJasa.getEstimasi_gaji().toString();
                String usia = String.valueOf(dataJasa.getUsia());
                String tanggal_lahir = dataUser.getTanggalLahir();
//                String tanggal_lahir = "aaa";
                String no_telp = String.valueOf(dataJasa.getNoTelp()) ;
                String email = dataJasa.getEmail();
                String status = dataJasa.getStatus();
                String pendidikan = dataJasa.getPengalaman_kerja();
                String alamat = dataJasa.getAlamat();

                Toast.makeText(holder.itemView.getContext(),"no_telp : "+no_telp,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,DetailUserInAdminActivity.class);
//                    String namaKategori = dataKategoriItem.getKategori();
//                    int id = dataKategoriItem.getId();
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
//                    bundle.putInt("id_kategori",id);

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pekerjaan = dataJasa.getPekerjaan();

                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Really Delete")
                        .setMessage("Are you sure want to delete kategori "+pekerjaan+" ?")
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(holder.itemView.getContext(),"will be delete soon",Toast.LENGTH_SHORT).show();

                                DataKategoriItem dataKategoriItem = (DataKategoriItem) holder.itemView.getTag();
                                int id = dataKategoriItem.getId();

                            }
                        }).create().show();
            }
        });

        holder.btn_validasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pekerjaan = dataJasa.getPekerjaan();

                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Really Validasi")
                        .setMessage("Are you sure want to validasi kategori "+pekerjaan+" ?")
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(holder.itemView.getContext(),"will be update soon",Toast.LENGTH_SHORT).show();

                                DataKategoriItem dataKategoriItem = (DataKategoriItem) holder.itemView.getTag();
                                int id = dataKategoriItem.getId();

                            }
                        }).create().show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return dataUserItems.size();
    }

}
