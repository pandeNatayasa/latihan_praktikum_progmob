package com.tr.nata.projectandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.fragment.FragmentHome;
import com.tr.nata.projectandroid.fragment.FragmentNewJob;
import com.tr.nata.projectandroid.fragment.FragmentFavorite;

public class HomeActivity extends AppCompatActivity {

//    //yang digunakan
//    TextView tv_namaUser;
//
//    private RecyclerView recyclerView;
//    private kategoriAdapter adapter;
//    DatabaseHelper myDb;
//
//    private List<ResponseKategori> responseKategoris = new ArrayList<>();
//
//    private List<DataKategoriItem> dataKategoriItems = new ArrayList<>();
//
//    ApiService service;
//    //akhir digunakan

    ApiClient apiClient;
    BottomNavigationView btn_nav_menu;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_nav_menu=findViewById(R.id.btn_nav_menu);
        btn_nav_menu.setOnNavigationItemSelectedListener(navListener);
//        Toolbar toolbar = findViewById(R.id.toolbar_home);
//        setSupportActionBar(toolbar);
//        setSupportActionBar(toolbar);
//        fragment = new Fragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,fragment).commit();

//        bottomBar.getbar().setBackgroundColor(getResources().getColor(R.color.bottom_))
//        setupBottomNavigationView();
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.btn_nav_menu);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);



        fragment=new FragmentHome();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,fragment).commit();
//        finish();
        //yang digunakan
//        tv_namaUser = findViewById(R.id.tv_nama);
//
//        service=ApiClient.getApiService();
//        myDb=new DatabaseHelper(this);
//        //akhir digunakan

//        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//
//        String email = sharedPref.getString("email","");
//
//        Call<user> call = service.dataUser(email)
//                call.enqueue();

//        Bundle bundle = getIntent().getExtras();
//        tv_namaUser.setText(bundle.getString("namaUser"));
//        tv_namaUser.setText(getIntent().getStringExtra("namaUser"));

        //yang digunakan
//        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//        String nama_user_login = sharedPref.getString("nama_user_login","");
//        tv_namaUser.setText(nama_user_login);
//
//        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_kategori);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//        Button btn_logout = (Button) findViewById(R.id.btn_logout);
//        Button btn_viewKategoriLokal = findViewById(R.id.btn_viewKategoriLokal);
//
//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Boolean login = false;
//                SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putBoolean("status_login",login);
//                editor.putString("status_login_string", String.valueOf(login));
//                editor.apply();
//
//                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btn_viewKategoriLokal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor res = myDb.getAllData();
//                if (res.getCount()==0){
//                    //show message
//                    showMessage("Eror","Nothing Found");
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()){
//                    buffer.append("Id : "+res.getString(0)+"\n");
//                    buffer.append("Kategori : "+res.getString(1)+"\n");
//                }
//                //show all data
//                showMessage("Data",buffer.toString());
//            }
//        });
//
//        callKategoriLokal();
//        callApi();

    }

//    private void setupBottomNavigationView() {
//        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.btn_nav_menu);
//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case
//                }
//            }
//        });
//    }

//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
//        return true;
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    fragment=new FragmentHome();
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            fragment = new FragmentHome();
//                            getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,fragment).commit();
                            break;
                        case R.id.nav_list:
                            fragment=new FragmentNewJob();
//                            getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,fragment).commit();
                            break;
                        case R.id.nav_favorite:
                            fragment=new FragmentFavorite();
//                            Intent intent = new Intent(getApplicationContext(),TryPerofilleActivity.class);
//                            startActivity(intent);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_layout,fragment).commit();
//                    finish();
                    return true;
                }
            };
//    @Override
//    public void onBackPressed(){
//        new AlertDialog.Builder(this)
//                .setTitle("Really Exit")
//                .setMessage("Are you sure want to exit ?")
//                .setNegativeButton(android.R.string.no,null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        HomeActivity.super.onBackPressed();
//                        finishAffinity();
//                        System.exit(0);
//                    }
//                }).create().show();
//    }


//    private void callApi(){
//        service.getKategori()
//                .enqueue(new Callback<ResponseKategori>() {
//                    @Override
//                    public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
//                        if (response.isSuccessful()){
//                            DatabaseHelper dbHelper = new DatabaseHelper(HomeActivity.this);
//
//                            dbHelper.deleteKategori();
//
//                            dataKategoriItems=response.body().getDataKategori();
//
//                            for (DataKategoriItem dataKategoriItem:dataKategoriItems){
//                                dbHelper.insertDataKategori(dataKategoriItem.getId(),dataKategoriItem.getKategori());
//                            }
//                            setAdapter();
////                            recyclerView.notifyAll();
//
////                            responseKategoris.addAll((Collection<? extends ResponseKategori>) response.body());
//
////                            adapter=new kategoriAdapter(responseKategoris,this);
////                            recyclerView.setAdapter(adapter);
////                            dataKategoriItems = response.body().getDataKategori();
////                            adapter = new kategoriAdapter(dataKategoriItems,this);
////                            recyclerView.setAdapter(adapter);
//                        }else {
//                            Toast.makeText(getApplicationContext(),"login gagal",Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseKategori> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),"login gagal koneksi",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//    }
//
//    private void setAdapter(){
//        adapter = new kategoriAdapter(this,dataKategoriItems);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void callKategoriLokal(){
//        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        dataKategoriItems=dbHelper.selectKategori();
//        setAdapter();
//    }
//
//    public void showMessage(String title, String message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//    }
    //akhir yang digunakan
}
