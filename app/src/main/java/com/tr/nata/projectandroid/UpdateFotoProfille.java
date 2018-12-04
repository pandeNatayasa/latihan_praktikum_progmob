package com.tr.nata.projectandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;

public class UpdateFotoProfille extends AppCompatActivity {
    String path;
    Bundle bundle;

    EditText et_nama_new_foto;
    ImageView img_new_foto;
    Button btn_update_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_foto_profille);

        bundle = getIntent().getExtras();
        String path = bundle.getString("path");
        String filename = bundle.getString("filename");

        et_nama_new_foto=findViewById(R.id.et_foto_profille);
        img_new_foto=findViewById(R.id.img_new_foto_profille);
        btn_update_foto=findViewById(R.id.btn_update_foto_profille);

        Toolbar toolbar = findViewById(R.id.toolbarid_new_foto_profille);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_nama_new_foto.setText(filename);
        Glide.with(UpdateFotoProfille.this).load(path).into(img_new_foto);
        btn_update_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateFotoProfille.this,"will be update",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
