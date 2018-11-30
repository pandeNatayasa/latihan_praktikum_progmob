package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.model.DataJasaItem;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            tv_nama_user=itemView.findViewById(R.id.tv_nama_user_inSubHomeAdmin);
            tv_pekerjaan=itemView.findViewById(R.id.tv_pekerjaan_inSubHomeAdmin);
            btn_validasi=itemView.findViewById(R.id.btn_validasi_inSubHomeAdmin);
            btn_hapus=itemView.findViewById(R.id.btn_delete_data_jasa_inSubHomeAdmin);

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

    }

    @Override
    public int getItemCount(){
        return dataUserItems.size();
    }

}
