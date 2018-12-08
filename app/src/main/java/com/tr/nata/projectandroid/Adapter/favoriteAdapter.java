package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
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
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;
import com.tr.nata.projectandroid.model.ResponseFavorite;

import java.util.ArrayList;
import java.util.List;

public class favoriteAdapter extends RecyclerView.Adapter<favoriteAdapter.ViewHolder> {

    private Context context;
    private List<ResponseFavorite> responseFavorites;
//    public TextView tv_data_user_inFavorite, tv_data_jasa_in_favorite;
    private CardView cardView_data_Favorite;
    ImageView img_delete;
    DatabaseHelper mydb;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_data_user_inFavorite, tv_data_jasa_inFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            mydb = new DatabaseHelper(itemView.getContext());
            tv_data_user_inFavorite=itemView.findViewById(R.id.tv_data_user_inFavorite);
            tv_data_jasa_inFavorite=itemView.findViewById(R.id.tv_data_jasa_user_inFavorite);
            cardView_data_Favorite=itemView.findViewById(R.id.cardView_data_jasa_favorite);
            img_delete=itemView.findViewById(R.id.btn_delete_data_jasa_inFavorite);

            cardView_data_Favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Pekerjaan : ",Toast.LENGTH_SHORT).show();
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

        DataUserItem dataUserItem = mydb.selectDataUser(responseFavorite.getIdUser());
        DataJasaItem dataJasaItem = mydb.selectOneDatajasa(responseFavorite.getIdDataJasa());


//        holder.tv_data_user_inFavorite.setText(String.valueOf(responseFavorite.getIdUser()));
        holder.tv_data_user_inFavorite.setText(dataUserItem.getName());
        holder.tv_data_jasa_inFavorite.setText(dataJasaItem.getPekerjaan());
//        holder.textView_pekerjaan.setText(dataJasaUser.getPekerjaan());
    }

    @Override
    public int getItemCount(){
        return responseFavorites.size();
    }

}
