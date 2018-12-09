package com.tr.nata.projectandroid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//import butterknife.BindView;
//import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class AddUserActivity extends AppCompatActivity{

    private Button btnAdd;
    private EditText etUserName,etUserEmail,etUserPassword,etUserJenisKelamin, etUserNoTelp, etUserTanggalLahir;
    TextView tv_error;
    RadioGroup rgJenisKelamin;
    RadioButton rbjenis_kelamin_Laki,rbjenis_kelamin_Perempuan;
    String jk="";
    TextView tv_login;
    private static final String TAG = "AddUserActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    ImageView imgTanggalLahir;

    ApiService service;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("Add User");

        service = ApiClient.getApiService();

        etUserName = findViewById(R.id.etUserName);
        etUserEmail = findViewById(R.id.etUserMail);
        etUserPassword=findViewById(R.id.etUserPassword);
//        etUserJenisKelamin=findViewById(R.id.etUserJenisKelamin);
        etUserNoTelp=findViewById(R.id.etUserNoTelp);
        etUserTanggalLahir=findViewById(R.id.etUserTanggalLahir);
        tv_login=findViewById(R.id.tv_login);

        rgJenisKelamin=(RadioGroup) findViewById(R.id.rgJenisKelamin);
        int selectedId = rgJenisKelamin.getCheckedRadioButtonId();
//        rbjenis_kelamin=(RadioButton) findViewById(selectedId);
        rbjenis_kelamin_Laki=findViewById(R.id.rbLakiLaki);
        rbjenis_kelamin_Perempuan=findViewById(R.id.rbPerempuan);

//        jk = rbjenis_kelamin.getText().toString();
        tv_error=findViewById(R.id.tv_error);
        imgTanggalLahir = findViewById(R.id.img_tanggal_lahir);

        btnAdd=findViewById(R.id.register);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        imgTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                  AddUserActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month+ 1;
                String date = year + "-"+month+"-"+dayOfMonth;
                etUserTanggalLahir.setText(date);
            }
        };
    }

    private void addUser(){
        String name = etUserName.getText().toString();
        String email = etUserEmail.getText().toString();
        String password = etUserPassword.getText().toString();
        String jenis_kelamin;
        if (rbjenis_kelamin_Laki.isChecked()){
            jenis_kelamin = "L";
        }else{
            jenis_kelamin = "P";
        }
        String no_telp=etUserNoTelp.getText().toString();
        String tanggal_lahir=etUserTanggalLahir.getText().toString();
        String status = String.valueOf("0");

        service.addUser(name,email,password,jenis_kelamin,no_telp,tanggal_lahir,status)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AddUserActivity.this,"Success",Toast.LENGTH_SHORT).show();

                            Intent intent =new Intent(AddUserActivity.this,LoginActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(AddUserActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(AddUserActivity.this,"Error Koneksi"+t,Toast.LENGTH_SHORT).show();
                        tv_error.setText("eror : "+t);
                    }
                });
//        Toast.makeText(this,"Name : "+name+", email : "+email,Toast.LENGTH_SHORT).show();
    }
}
