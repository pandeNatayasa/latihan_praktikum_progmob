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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.DetailUserActivity;
import com.tr.nata.projectandroid.DetailUserInAdminActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;

import java.util.List;

public class ListDataJasaInUserAdapter extends RecyclerView.Adapter<ListDataJasaInUserAdapter.ViewHolder> {

    private List<ResponseDataJasaUser> dataJasaUsers;
    private Context context;
    private CardView cardViewDataJasa;
    ImageView img_edit, img_delete;
    String id_kategori,id_user,pekerjaan,estimasi_gaji,pengalaman_kerja,
            usia,no_telp,email,status,alamat,id_kecamatan;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText et_id_kategori,et_pekerjaan,et_estimasi_gaji,et_pengalaman_kerja,
            et_usia,et_no_telp,et_email,et_status,et_alamat,et_id_kecamatan;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_pekerjaan;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            context = itemView.getContext();

            textView_pekerjaan=itemView.findViewById(R.id.tv_data_jasa_user_inProfille);
            cardViewDataJasa=itemView.findViewById(R.id.cardView_data_jasa_inProfille);
            img_delete=itemView.findViewById(R.id.btn_delete_data_jasa_inProfille);

            cardViewDataJasa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ResponseDataJasaUser responseDataJasaUser=(ResponseDataJasaUser) itemView.getTag();
                    SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);

                    int id_user_login = sharedPref.getInt("id_user_login",0);
                    Toast.makeText(itemView.getContext(),"Pekerjaan : "+responseDataJasaUser.getPekerjaan(),Toast.LENGTH_SHORT).show();

                    String nama = sharedPref.getString("nama_user_login","");
//                    String nama ="aa";
                    String jasa = responseDataJasaUser.getPekerjaan();
                    String gaji = String.valueOf(responseDataJasaUser.getEstimasiGaji());
                    String usia = String.valueOf(responseDataJasaUser.getUsia());
                    String tanggal_lahir = sharedPref.getString("tanggal_lahir_user_login","");
//                String tanggal_lahir = "aaa";
                    String no_telp = String.valueOf(responseDataJasaUser.getNoTelp()) ;
                    String email = responseDataJasaUser.getEmail();
                    String status = responseDataJasaUser.getStatus();
                    String pendidikan = responseDataJasaUser.getPengalamanKerja();
                    String alamat = responseDataJasaUser.getAlamat();

                    Toast.makeText(itemView.getContext(),"no_telp : "+no_telp,Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context,DetailUserInAdminActivity.class);
//                    String namaKategori = dataKategoriItem.getKategori();
//                    int id = dataKategoriItem.getId();
                    Bundle bundle = new Bundle();
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


    public ListDataJasaInUserAdapter(Context context,List<ResponseDataJasaUser> responseDataJasaUsers){
        this.dataJasaUsers=responseDataJasaUsers;
        this.context=context;
    }

    @NonNull
    @Override
    public ListDataJasaInUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_data_jasa_in_profilleuser,parent,false);
        return new ListDataJasaInUserAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(dataJasaUsers.get(position));

        ResponseDataJasaUser dataJasaUser = dataJasaUsers.get(position);
        holder.textView_pekerjaan.setText(dataJasaUser.getPekerjaan());
    }

    @Override
    public int getItemCount(){
        return dataJasaUsers.size();
    }

}
