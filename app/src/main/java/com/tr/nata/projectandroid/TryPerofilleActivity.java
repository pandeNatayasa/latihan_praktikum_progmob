package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tr.nata.projectandroid.Helper.ViewPagerAdapter;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;

public class TryPerofilleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    ImageView img_logout;
    private TextView tv_nama_profille;
    ImageView img_change_foto_profille,img_fotoProfille;
//    private ViewPager viewPager;
    String path;

    private int[] tabIcons = {
            R.drawable.ic_view_list_white,
            R.drawable.ic_person_white
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profille);

        int fragmentId = getIntent().getIntExtra("Fragment_id", 0);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout_profille);
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        img_change_foto_profille=findViewById(R.id.img_change_fotoprofille);
        img_fotoProfille=findViewById(R.id.img_foto_profille_inProfille);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid_profille);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout=findViewById(R.id.appbarLayout_id);
        img_logout=findViewById(R.id.img_logout);

        tv_nama_profille=findViewById(R.id.tv_nama_proflle_inProfille);
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        String foto_profille = sharedPref.getString("user_foto_profille","");
        String url = "http://172.17.100.2:8000"+foto_profille;
        Glide.with(this).load(url).into(img_fotoProfille);
        tv_nama_profille.setText(nama_user_login);

        final ViewPager viewPager=findViewById(R.id.viewPager_1);

//        Bundle bundle = new Bundle();
//        bundle.putString("TARGET_FRAGMENT_ID", fragmentId);
//        TabF tabFragment = new TabFragment();
//        tabFragment.setArguments(bundle);
//        getSupportFragmentManager().beginTransaction().replace(R.id.confrag, tabFragment).commit();
//        tabLayout.setTabGravity(fragmentId);

        final ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        img_change_foto_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), UpdateFotoProfille.class);
//                startActivity(intent);
                selectImage();
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        setupTabIcons();


    }

    public void logout(){
        new AlertDialog.Builder(TryPerofilleActivity.this)
                .setTitle("Really Logout")
                .setMessage("Are you sure want to logout ?")
                .setNegativeButton(android.R.string.no,null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                        sharedPref.edit().clear().commit();

//                        editor.putString("status_login_string", String.valueOf(response.body().isStatus()));
//                        editor.putInt("id_user_login",response.body().getDataUser().getId());
//                        editor.putString("nama_user_login", String.valueOf(response.body().getDataUser().getName()));
//                        editor.putString("email_user_login",String.valueOf(response.body().getDataUser().getEmail()));
//                        editor.putString("jk_user_login", String.valueOf(response.body().getDataUser().getJenisKelamin()));
//                        editor.putString("no_telp_user_login", String.valueOf(response.body().getDataUser().getNoTelp()));
//                        editor.putString("tanggal_lahir_user_login", String.valueOf(response.body().getDataUser().getTanggalLahir()));
//                        editor.putString("status_user",String.valueOf(response.body().getDataUser().getStatusUser()));

                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();

    }

    private void setupTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Select picture, from album.
     */
    private void selectImage() {
        Album.image(this)
                .singleChoice()
                .camera(true)
                .widget(
                        Widget.newDarkBuilder(this)
                                .build()
                )
                .onResult((Action<ArrayList<AlbumFile>>) result -> {
                    path = result.get(0).getPath();
                    Toast.makeText(TryPerofilleActivity.this,"path : "+path,Toast.LENGTH_SHORT).show();
                    String filename = path.substring(path.lastIndexOf("/")+1);
//                    et_logo_kategori.setText(filename);
//                    mAlbumFiles = result;
//                    Bundle bundle
                    Intent intent = new Intent(getApplicationContext(),UpdateFotoProfille.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("path", path);
                    bundle.putString("filename", filename);
                    intent.putExtras(bundle);
                    startActivity(intent);
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Toast.makeText(TryPerofilleActivity.this, "cancell", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }

}
