package com.tr.nata.projectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class DetailUserActivity extends AppCompatActivity{

    TextView tv_nama, tv_jasa, tv_gaji, tv_usia, tv_tanggal_lahir,
            tv_no_telp,tv_email,tv_status,tv_pendidikan,tv_alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        tv_nama=(TextView)findViewById(R.id.tv_user_name);
        tv_jasa=(TextView)findViewById(R.id.tv_user_pekerjaan);
        tv_gaji=(TextView)findViewById(R.id.tv_user_gaji);
        tv_usia=(TextView)findViewById(R.id.tv_user_usia);
        tv_tanggal_lahir=(TextView)findViewById(R.id.tv_user_tanggal_lahir);
        tv_no_telp=(TextView)findViewById(R.id.tv_user_noTelp);
        tv_email=(TextView)findViewById(R.id.tv_user_email);
        tv_status=(TextView)findViewById(R.id.tv_user_status);
        tv_pendidikan=(TextView)findViewById(R.id.tv_user_pendidikan);
        tv_alamat=(TextView)findViewById(R.id.tv_user_alamat);

        Bundle bundle = getIntent().getExtras();

        String jasa = bundle.getString("jasa");
        Toast.makeText(DetailUserActivity.this," jasa :"+jasa,Toast.LENGTH_SHORT).show();
//        tv_jasa.setText(jasa);
        tv_nama.setText(bundle.getString("nama"));
        tv_jasa.setText(bundle.getString("jasa"));
        tv_gaji.setText(bundle.getString("gaji"));
        tv_usia.setText(bundle.getString("usia"));
        tv_tanggal_lahir.setText(bundle.getString("tanggal_lahir"));
        tv_no_telp.setText(bundle.getString("no_telp"));
        tv_email.setText(bundle.getString("email"));
        tv_status.setText(bundle.getString("status"));
        tv_pendidikan.setText(bundle.getString("pendidikan"));
        tv_alamat.setText(bundle.getString("alamat"));

    }
}
