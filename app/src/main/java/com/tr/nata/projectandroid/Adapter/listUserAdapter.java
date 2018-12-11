package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.DetailUserActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.SubHomeActivity;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasa;

import java.util.List;

public class listUserAdapter extends RecyclerView.Adapter<listUserAdapter.ViewHolder> {

    private List<DataJasaItem> dataJasaItems;
    private List<DataUserItem> dataUserItems;
    private Context context;
    private CardView listDataJasa;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nama_user, pekerjaan;

        public ViewHolder(View itemView){
            super(itemView);

            context=itemView.getContext();

            nama_user=(TextView)itemView.findViewById(R.id.tv_nama_user);
            pekerjaan=(TextView)itemView.findViewById(R.id.tv_pekerjaan);

            listDataJasa = (CardView)itemView.findViewById(R.id.cardView_list_user);
//            listDataJasa.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    DataJasaItem dataJasaItem = (DataJasaItem) itemView.getTag();
//                    DataUserItem dataUserItem = (DataUserItem) itemView.getTag();
//
//                    String nama = dataUserItem.getName();
////                    String nama ="aa";
//                    String jasa = dataJasaItem.getPekerjaan();
//                    String gaji = "0000000";
//                    String usia = String.valueOf(dataJasaItem.getUsia());
////                    String tanggal_lahir = dataUserItem.getTanggalLahir().toString();
//                    String tanggal_lahir = "aaa";
//                    String no_telp = String.valueOf(dataJasaItem.getNoTelp()) ;
//                    String email = dataJasaItem.getEmail();
//                    String status = dataJasaItem.getStatus();
//                    String pendidikan = "belum isi di database";
//                    String alamat = dataJasaItem.getAlamat();
//
//                    Toast.makeText(itemView.getContext(),"no_telp : "+no_telp,Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(context,DetailUserActivity.class);
////                    String namaKategori = dataKategoriItem.getKategori();
////                    int id = dataKategoriItem.getId();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("nama", nama);
//                    bundle.putString("jasa",jasa);
//                    bundle.putString("gaji",gaji);
//                    bundle.putString("usia",usia);
//                    bundle.putString("tanggal_lahir",tanggal_lahir);
//                    bundle.putString("no_telp",no_telp);
//                    bundle.putString("email",email);
//                    bundle.putString("status",status);
//                    bundle.putString("pendidikan",pendidikan);
//                    bundle.putString("alamat",alamat);
////                    bundle.putInt("id_kategori",id);
//
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//
//                }
//            });
        }

    }

//    public listUserAdapter(List<DataJasaItem> dataJasaItems,List<DataUserItem> dataUserItems,Context context){
//        this.dataJasaItems =dataJasaItems;
//        this.dataUserItems=dataUserItems;
//        this.context=context;
//    }
    public listUserAdapter(List<DataJasaItem> dataJasaItems,List<DataUserItem> dataUserItems,Context context){
        this.dataJasaItems =dataJasaItems;
        this.dataUserItems=dataUserItems;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_user_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(dataUserItems.get(position));
        holder.itemView.setTag(dataJasaItems.get(position));

        DataJasaItem dataJasa = dataJasaItems.get(position);
        DataUserItem dataUser = dataUserItems.get(position);
        Log.d("position","ke-"+position);
        holder.pekerjaan.setText(dataJasa.getPekerjaan());
        holder.nama_user.setText(dataUser.getName());

        listDataJasa.setOnClickListener(new View.OnClickListener() {
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

//                Toast.makeText(holder.itemView.getContext(),"no_telp : "+no_telp,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,DetailUserActivity.class);
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

    }

    @Override
    public int getItemCount(){
        return dataJasaItems.size();
    }
}
