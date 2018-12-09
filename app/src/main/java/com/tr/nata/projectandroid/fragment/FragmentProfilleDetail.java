package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.EditProfilleActivity;
import com.tr.nata.projectandroid.R;

public class FragmentProfilleDetail extends Fragment {

    TextView tv_name, tv_email, tv_jenis_kelamin, tv_notelp, tv_tanggal_lahir;
    FloatingActionButton fab_edit_profiile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profille_detail,container,false);

        tv_name=view.findViewById(R.id.tv_name_profille);
        tv_email=view.findViewById(R.id.tv_email_profille);
        tv_jenis_kelamin=view.findViewById(R.id.tv_jk_profille);
        tv_notelp=view.findViewById(R.id.tv_notelp_profille);
        tv_tanggal_lahir=view.findViewById(R.id.tv_tanggal_lahir_profille);
        fab_edit_profiile=view.findViewById(R.id.fab_edit_profille);

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        String email_user_login = sharedPref.getString("email_user_login","");
        String jk_user_login = sharedPref.getString("jk_user_login","");
        String no_telp_user_login = sharedPref.getString("no_telp_user_login","");
        String tanggal_lahir_user_login = sharedPref.getString("tanggal_lahir_user_login","");

        tv_name.setText(nama_user_login);
        tv_email.setText(email_user_login);
        tv_jenis_kelamin.setText(jk_user_login);
        tv_notelp.setText(no_telp_user_login);
        tv_tanggal_lahir.setText(tanggal_lahir_user_login);

        fab_edit_profiile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"will be update",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),EditProfilleActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
