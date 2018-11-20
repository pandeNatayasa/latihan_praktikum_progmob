package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseDataJasa;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;

import java.util.List;

public class dataJasaUserAdapter extends RecyclerView.Adapter<dataJasaUserAdapter.ViewHolder> {

    private List<ResponseDataJasaUser> dataJasaUsers;
    private Context context;
    private CardView cardViewDataJasa;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_pekerjaan;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            context = itemView.getContext();

            textView_pekerjaan=itemView.findViewById(R.id.tv_data_jasa_user);
            cardViewDataJasa=itemView.findViewById(R.id.cardView_data_jasa_user);
            cardViewDataJasa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ResponseDataJasaUser responseDataJasaUser=(ResponseDataJasaUser) itemView.getTag();
                    Toast.makeText(itemView.getContext(),"Pekerjaan : "+responseDataJasaUser.getPekerjaan(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public dataJasaUserAdapter(Context context,List<ResponseDataJasaUser> responseDataJasaUsers){
        this.dataJasaUsers=responseDataJasaUsers;
        this.context=context;
    }

    @NonNull
    @Override
    public dataJasaUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_data_jasa_user_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dataJasaUserAdapter.ViewHolder holder, int position){
        holder.itemView.setTag(dataJasaUsers.get(position));

        ResponseDataJasaUser dataJasaUser = dataJasaUsers.get(position);
        holder.textView_pekerjaan.setText(dataJasaUser.getPekerjaan());
    }

    @Override
    public int getItemCount(){
        return dataJasaUsers.size();
    }
}
