package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.tr.nata.projectandroid.Helper.ViewPagerAdapter;

public class TryPerofilleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    ImageView img_logout;
    private TextView tv_nama_profille;
//    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_view_list_white,
            R.drawable.ic_person_white
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profille);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout_profille);
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid_profille);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout=findViewById(R.id.appbarLayout_id);
        img_logout=findViewById(R.id.img_logout);

        tv_nama_profille=findViewById(R.id.tv_nama_proflle_inProfille);
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        tv_nama_profille.setText(nama_user_login);

        final ViewPager viewPager=findViewById(R.id.viewPager_1);

        final ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
}