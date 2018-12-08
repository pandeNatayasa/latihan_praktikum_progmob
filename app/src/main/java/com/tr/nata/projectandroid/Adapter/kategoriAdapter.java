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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tr.nata.projectandroid.HomeActivity;
import com.tr.nata.projectandroid.LoginActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.SubHomeActivity;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseKategori;

import java.util.List;

import retrofit2.Callback;

public class kategoriAdapter extends RecyclerView.Adapter<kategoriAdapter.ViewHolder> {

    private List<DataKategoriItem> dataKategoriItems;
    private Context context;
    private CardView listKategori;

    /////// belum diedit
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_kategori_1;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            context = itemView.getContext();

            tv_kategori_1=(TextView) itemView.findViewById(R.id.tv_kategori_1);
            imageView = (ImageView) itemView.findViewById(R.id.img_kategori);
            listKategori=(CardView)itemView.findViewById(R.id.cardView_kategori);

            listKategori.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();

                    Intent intent = new Intent(context,SubHomeActivity.class);
                    String namaKategori = dataKategoriItem.getKategori();
                    int id = dataKategoriItem.getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("namaKategori", namaKategori);
                    bundle.putInt("id_kategori",id);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    DataKategoriItem dataKategoriItem = (DataKategoriItem) itemView.getTag();
//
////                    int idkategori = dataKategoriItem.getId();
//                    String namaKategori = dataKategoriItem.getKategori();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("namaUser", namaKategori);
//
//                    Intent intent = new Intent(itemView.getContext(), SubHomeActivity.class);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//
////                    Toast.makeText(itemView.getContext()," "+dataKategoriItem.getKategori(),Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    public kategoriAdapter(Context context,List<DataKategoriItem> dataKategoriItems){
        this.dataKategoriItems=dataKategoriItems;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_kategori_layout,parent,false);
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
        holder.tv_kategori_1.setText(dataKategoriItem.getKategori());
        String url = "http://172.17.100.2:8000"+dataKategoriItem.getLogoKategori();
        Glide.with(holder.itemView).load(url).into(holder.imageView);

    }

    @Override
    public int getItemCount(){
        return dataKategoriItems.size();
    }


}
