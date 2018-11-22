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
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseDataJasa;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;

import java.util.List;

public class dataJasaUserAdapter extends RecyclerView.Adapter<dataJasaUserAdapter.ViewHolder> {

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

            textView_pekerjaan=itemView.findViewById(R.id.tv_data_jasa_user);
            cardViewDataJasa=itemView.findViewById(R.id.cardView_data_jasa_user);
            img_edit=itemView.findViewById(R.id.btn_edit_data_jasa);
            img_delete=itemView.findViewById(R.id.btn_delete_data_jasa);

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

                    Intent intent = new Intent(context,DetailUserActivity.class);
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

            img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ResponseDataJasaUser responseDataJasaUser=(ResponseDataJasaUser) itemView.getTag();
                    SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);

                    int id_user_login = sharedPref.getInt("id_user_login",0);
                    id_kategori=String.valueOf(responseDataJasaUser.getIdKategori());
                    id_user=String.valueOf(id_user_login);
                    pekerjaan=responseDataJasaUser.getPekerjaan();
                    estimasi_gaji=String.valueOf(responseDataJasaUser.getEstimasiGaji());
                    pengalaman_kerja=responseDataJasaUser.getPengalamanKerja();
                    usia=String.valueOf(responseDataJasaUser.getUsia());
                    no_telp=responseDataJasaUser.getNoTelp();
                    email=responseDataJasaUser.getEmail();
                    status=responseDataJasaUser.getStatus();
                    alamat=responseDataJasaUser.getAlamat();
                    id_kecamatan=String.valueOf(responseDataJasaUser.getIdKecamatan());

                    dialog= new AlertDialog.Builder(itemView.getContext());
                    inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    dialogView = inflater.inflate(R.layout.activity_add_job,null);
                    dialog.setView(dialogView);
                    dialog.setCancelable(true);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("Edit Data Jasa");

                    et_id_kategori=(EditText) dialogView.findViewById(R.id.et_kategori_new);
                    et_pekerjaan=dialogView.findViewById(R.id.et_pekerjaan_new);
                    et_estimasi_gaji=dialogView.findViewById(R.id.et_estimasi_gaji_new);
                    et_pengalaman_kerja=dialogView.findViewById(R.id.et_pengalaman_kerja_new);
                    et_usia=dialogView.findViewById(R.id.et_usia_new);
                    et_no_telp=dialogView.findViewById(R.id.et_no_telp_new);
                    et_email=dialogView.findViewById(R.id.et_email_new);
                    et_status=dialogView.findViewById(R.id.et_status_new);
                    et_alamat=dialogView.findViewById(R.id.et_alamat_new);
                    et_id_kecamatan=dialogView.findViewById(R.id.et_kecamatan_new);

                    et_id_kategori.setText(id_kategori);
                    et_pekerjaan.setText(pekerjaan);
                    et_estimasi_gaji.setText(estimasi_gaji);
                    et_pengalaman_kerja.setText(pengalaman_kerja);
                    et_usia.setText(usia);
                    et_no_telp.setText(no_telp);
                    et_email.setText(email);
                    et_status.setText(status);
                    et_alamat.setText(alamat);
                    et_id_kecamatan.setText(id_kecamatan);


                    dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(itemView.getContext(),"will be update soon",Toast.LENGTH_SHORT).show();

                            //membersihkan edit text
                            et_id_kategori.setText(null);
                            et_pekerjaan.setText(null);
                            et_estimasi_gaji.setText(null);
                            et_pengalaman_kerja.setText(null);
                            et_usia.setText(null);
                            et_no_telp.setText(null);
                            et_email.setText(null);
                            et_status.setText(null);
                            et_alamat.setText(null);
                            et_id_kecamatan.setText(null);
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



//            public void Data_jasa_Form(){
//                dialog= new AlertDialog.Builder(itemView.getContext());
//                inflater =itemView.getContext()
//            }
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
