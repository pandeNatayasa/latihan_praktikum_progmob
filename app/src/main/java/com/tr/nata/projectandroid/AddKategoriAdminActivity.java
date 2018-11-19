package com.tr.nata.projectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tr.nata.projectandroid.Database.DatabaseHelper;

public class AddKategoriAdminActivity extends AppCompatActivity {
    DatabaseHelper mydb;

    private Button btn_addkategori;
    private EditText et_addkategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kategory_admin);

        btn_addkategori=findViewById(R.id.btn_addkategori);
        et_addkategori=findViewById(R.id.et_addkategori);

        mydb=new DatabaseHelper(this);

        addDataKategori();
    }

    public void addDataKategori(){
        btn_addkategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydb.insertDataKategori(et_addkategori.getText().toString());
                if (isInserted=true){
                    Toast.makeText(AddKategoriAdminActivity.this,"Kategori berhasil di simpan",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(AddKategoriAdminActivity.this,"Kategori gagal di simpan",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
