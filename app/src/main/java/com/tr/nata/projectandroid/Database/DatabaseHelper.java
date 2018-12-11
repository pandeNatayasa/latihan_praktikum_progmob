package com.tr.nata.projectandroid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.DataUser;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;
import com.tr.nata.projectandroid.model.ResponseFavorite;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "praktikum.db";

    //Tabel Kategori
    public static final String TABLE_NAME_KATEGORI = "category_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_KATEGORI = "kategori";

    //Tabel Data Jasa
    public static final String TABLE_NAME_JASA = "data_jasa_table";
    public static final String COLUMN_ID_KATEGORI = "id_kategori";
    public static final String COLUMN_ID_USER = "id_user";
    public static final String COLUMN_PEKERJAAN = "pekerjaan";
    public static final String COLUMN_ESTIMASI_GAJI="estimasi_gaji";
    public static final String COLUMN_USIA="usia";
    public static final String COLUMN_NO_TELP="no_telp";
    public static final String COLUMN_EMAIL_JASA="email_jasa";
    public static final String COLUMN_STATUS="status";
    public static final String COLUMN_STATUS_VALIDASI="status_validasi";
    public static final String COLUMN_ALAMAT_JASA="alamat";
    public static final String COLUMN_PENGALAMAN_KERJA="pengalaman_kerja";

    //Tabel Data User
    public static final String TABLE_NAME_USER = "data_user_table";
    public static final String COLUMN_NAME_USER = "name";
    public static final String COLUMN_EMAIL_USER="email";
    public static final String COLUMN_JK_USER = "jenis_kelamin";
    public static final String COLUMN_NO_TELP_USER="no_telp";
    public static final String COLUMN_TANGGAL_LAHIR_USER = "tanggal_lahir";

    //Tabel Favorite
    public static final String TABLE_NAME_FAVORITE = "data_favorite_table";
    public static final String COLUMN_ID_USER_FAVORITE = "id_user";
    public static final String COLUMN_ID_DATA_JASA_FAVORITE = "id_data_jasa";


    //pembuatan database
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,12);
    }

    //pembuatan tabel
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_KATEGORI + "(id integer primary key autoincrement, kategori text);");

        db.execSQL("create table " + TABLE_NAME_JASA + "(" +
                DataJasaItem.Entry._ID+" integer primary key autoincrement," +
                COLUMN_ID +" integer," +
                COLUMN_ID_KATEGORI+" integer," +
                COLUMN_ID_USER+" integer," +
                COLUMN_PEKERJAAN+" text," +
                COLUMN_ESTIMASI_GAJI+" integer," +
                COLUMN_USIA+" integer," +
                COLUMN_NO_TELP+" text," +
                COLUMN_EMAIL_JASA+" text," +
                COLUMN_STATUS+" text," +
                COLUMN_STATUS_VALIDASI+" text," +
                COLUMN_ALAMAT_JASA+" text," +
                COLUMN_PENGALAMAN_KERJA+" text);");

        db.execSQL("create table "+TABLE_NAME_USER+"(" +
                DataUserItem.Entry._ID+" integer primary key autoincrement," +
                COLUMN_ID+" integer," +
                COLUMN_NAME_USER+" text," +
                COLUMN_EMAIL_USER+" text," +
                COLUMN_JK_USER+" text," +
                COLUMN_NO_TELP_USER+" text," +
                COLUMN_TANGGAL_LAHIR_USER+" text);");

        db.execSQL("create table "+TABLE_NAME_FAVORITE+"(" +
                ResponseFavorite.Entry._ID+" integer primary key autoincrement," +
                COLUMN_ID+" integer," +
                COLUMN_ID_USER_FAVORITE+" integer," +
                COLUMN_ID_DATA_JASA_FAVORITE+" integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_KATEGORI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_JASA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAVORITE);
        onCreate(db);
    }

    //Tabel Kategori
    public boolean insertDataKategori(int id,String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_KATEGORI,kategori);
        long result = db.insert(TABLE_NAME_KATEGORI,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean insertDataKategori(String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_KATEGORI,kategori);
        long result = db.insert(TABLE_NAME_KATEGORI,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_KATEGORI,null);
        return res;
    }

    public List<DataKategoriItem> selectKategori(){
        List<DataKategoriItem> dataKategoriItems = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlquery = "select * from "+ TABLE_NAME_KATEGORI;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlquery,null);
//        Cursor cursor=sqLiteDatabase.query(DataKategoriItem.Entry.TABLE_NAME_KATEGORI,null,null,null,null, null,null);

        int count = cursor.getCount();

//        if (count>0){
////            cursor.moveToFirst();
//            while (cursor.moveToNext()){
//                int id = cursor.getInt(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_ID));
//                String kategori = cursor.getString(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_KATEGORI));
//
//                DataKategoriItem temp = new DataKategoriItem();
//                temp.setId(id);
//                temp.setKategori(kategori);
//                dataKategoriItems.add(temp);
//            }
//
//        }

        //cara dari pak agung cahyawan
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_ID));
                String kategori = cursor.getString(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_KATEGORI));

                DataKategoriItem temp = new DataKategoriItem();
                temp.setId(id);
                temp.setKategori(kategori);
                dataKategoriItems.add(temp);
            }while (cursor.moveToNext());

        }
        cursor.close();
        sqLiteDatabase.close();
        return dataKategoriItems;
    }

    public boolean updateData(String id, String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_KATEGORI,kategori);

        db.update(TABLE_NAME_KATEGORI,contentValues,"ID = ?",new String[]{id});
        return true;
    }

    public void deleteKategori(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataKategoriItem.Entry.TABLE_NAME_KATEGORI,null,null);
    }

    //Untuk Tabel Data Jasa
    public boolean insertDataJasa(int id,int id_kategori,int id_user,String pekerjaan,
                                  int usia,String no_telp,String email_jasa,String status,String status_validasi,String alamat_jasa,String pengalaman_kerja,int estimasi_gaji){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_ID_KATEGORI,id_kategori);
        contentValues.put(COLUMN_ID_USER,id_user);
        contentValues.put(COLUMN_PEKERJAAN,pekerjaan);
        contentValues.put(COLUMN_ESTIMASI_GAJI,estimasi_gaji);
        contentValues.put(COLUMN_USIA,usia);
        contentValues.put(COLUMN_NO_TELP,no_telp);
        contentValues.put(COLUMN_EMAIL_JASA,email_jasa);
        contentValues.put(COLUMN_STATUS,status);
        contentValues.put(COLUMN_STATUS_VALIDASI,status_validasi);
        contentValues.put(COLUMN_ALAMAT_JASA,alamat_jasa);
        contentValues.put(COLUMN_PENGALAMAN_KERJA,pengalaman_kerja);
        long result = db.insert(TABLE_NAME_JASA,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getAllDataJasa(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_JASA,null);
        return res;
    }

    public void deleteJasa(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataJasaItem.Entry.TABLE_NAME_JASA,COLUMN_ID_KATEGORI+"="+id,null);
    }

    public void deleteJasainUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataJasaItem.Entry.TABLE_NAME_JASA,COLUMN_ID_USER+"="+id,null);
    }

    public void deleteJasaOne(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataJasaItem.Entry.TABLE_NAME_JASA,COLUMN_ID+"="+id,null);
    }

    public List<ResponseDataJasaUser> selectDatajasainUser(int id_user){
        List<ResponseDataJasaUser> dataJasaItems = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + TABLE_NAME_JASA + " where " + COLUMN_ID_USER + " = " + id_user;

        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);
        int count = cursor1.getCount();
        int id_data_jasa = 0;
        String pekerjaan= "a";
        if (count>0){
            cursor1.moveToFirst();

            do{
//                id_data_jasa = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ID));
                int id_user_data = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ID_USER));
                pekerjaan = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
                int estimasi_gaji = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ESTIMASI_GAJI));
                int usia = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_USIA));
                String no_telp = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_NO_TELP));
                String email = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_EMAIL_JASA));
                String status = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_STATUS));
                String alamat = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ALAMAT_JASA));
                String pengalaman_kerja = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PENGALAMAN_KERJA));

                ResponseDataJasaUser temp = new ResponseDataJasaUser();
//                temp.setId(id_data_jasa);
                temp.setPekerjaan(pekerjaan);
                temp.setEstimasiGaji(estimasi_gaji);
                temp.setIdUser(id_user_data);
                temp.setUsia(usia);
                temp.setNoTelp(no_telp);
                temp.setEmail(email);
                temp.setStatus(status);
                temp.setAlamat(alamat);
                temp.setPengalamanKerja(pengalaman_kerja);
                dataJasaItems.add(temp);
            }while (cursor1.moveToNext());
        }

        cursor1.close();
        sqLiteDatabase.close();
        return dataJasaItems;
    }

    public List<DataJasaItem> selectDatajasa(int id_kategori){
        List<DataJasaItem> dataJasaItems = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + TABLE_NAME_JASA + " where " + COLUMN_ID_KATEGORI + " = " + id_kategori;
//        Cursor c = db.rawQuery(sqlQuery,null);
//        Cursor cursor=sqLiteDatabase.query(DataJasaItem.Entry.TABLE_NAME_JASA,null,COLUMN_ID_KATEGORI+"="+id,null ,null, null,null);
        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);
        int count = cursor1.getCount();
        int id_data_jasa = 0;
        String pekerjaan= "a";
        if (count>0){
            cursor1.moveToFirst();
//            id_data_jasa=cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ID));
//            pekerjaan=cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
//
//            DataJasaItem temp = new DataJasaItem();
//            temp.setId(id_data_jasa);
//            temp.setPekerjaan(pekerjaan);
//            dataJasaItems.add(temp);
            do{
//                id_data_jasa = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ID));
                int id_user = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ID_USER));
                pekerjaan = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
                int estimasi_gaji = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ESTIMASI_GAJI));
                int usia = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_USIA));
                String no_telp = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_NO_TELP));
                String email = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_EMAIL_JASA));
                String status = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_STATUS));
                String alamat = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ALAMAT_JASA));
                String pengalaman_kerja = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PENGALAMAN_KERJA));

                DataJasaItem temp = new DataJasaItem();
//                temp.setId(id_data_jasa);
                temp.setPekerjaan(pekerjaan);
                temp.setEstimasi_gaji(estimasi_gaji);
                temp.setIdUser(id_user);
                temp.setUsia(usia);
                temp.setNoTelp(no_telp);
                temp.setEmail(email);
                temp.setStatus(status);
                temp.setAlamat(alamat);
                temp.setPengalaman_kerja(pengalaman_kerja);
                dataJasaItems.add(temp);
            }while (cursor1.moveToNext());
        }
//        if (count>0){
//            cursor.moveToFirst();
//            while (cursor.moveToNext()){
//                int id_data_jasa = cursor.getInt(cursor.getColumnIndex(DataJasaItem.Entry.COLUMN_ID));
//                String pekerjaan = cursor.getString(cursor.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
//
//                DataJasaItem temp = new DataJasaItem();
//                temp.setId(id_data_jasa);
//                temp.setPekerjaan(pekerjaan);
//                dataJasaItems.add(temp);
//            }
//
//        }
        cursor1.close();
        sqLiteDatabase.close();
        return dataJasaItems;
    }

    public DataJasaItem selectOneDatajasa(int id_data_jasa){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + TABLE_NAME_JASA + " where " + COLUMN_ID + " = " + id_data_jasa;
//        Cursor c = db.rawQuery(sqlQuery,null);
//        Cursor cursor=sqLiteDatabase.query(DataJasaItem.Entry.TABLE_NAME_JASA,null,COLUMN_ID_KATEGORI+"="+id,null ,null, null,null);
        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);

        cursor1.moveToFirst();
        int id_user = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ID_USER));
        String pekerjaan = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
        int estimasi_gaji = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ESTIMASI_GAJI));
        int usia = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_USIA));
        String no_telp = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_NO_TELP));
        String email = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_EMAIL_JASA));
        String status = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_STATUS));
        String alamat = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ALAMAT_JASA));
        String pengalaman_kerja = cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PENGALAMAN_KERJA));

        DataJasaItem temp = new DataJasaItem();
//                temp.setId(id_data_jasa);
        temp.setPekerjaan(pekerjaan);
        temp.setEstimasi_gaji(estimasi_gaji);
        temp.setIdUser(id_user);
        temp.setUsia(usia);
        temp.setNoTelp(no_telp);
        temp.setEmail(email);
        temp.setStatus(status);
        temp.setAlamat(alamat);
        temp.setPengalaman_kerja(pengalaman_kerja);

        cursor1.close();
        sqLiteDatabase.close();
        return temp;
    }

    public String jumlah_data_jasa(int id_kategori){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + TABLE_NAME_JASA + " where " + COLUMN_ID_KATEGORI + " = " + id_kategori;
//        Cursor c = db.rawQuery(sqlQuery,null);
//        Cursor cursor=sqLiteDatabase.query(DataJasaItem.Entry.TABLE_NAME_JASA,null,COLUMN_ID_KATEGORI+"="+id,null ,null, null,null);
        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);
        int count = cursor1.getCount();
        String data_jasa="a";
        if (count>0) {
            cursor1.moveToFirst();
             data_jasa= cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
        }
//        String kategori = cursor.getString(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_KATEGORI));
        cursor1.close();
        sqLiteDatabase.close();
        return data_jasa;
    }

    //Untuk Tabel Data User
    public boolean insertDataUser(int id,String name,String email,String jenis_kelamin, String no_telp,String tanggal_lahir){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_NAME_USER,name);
        contentValues.put(COLUMN_EMAIL_USER,email);
        contentValues.put(COLUMN_JK_USER,jenis_kelamin);
        contentValues.put(COLUMN_NO_TELP_USER,no_telp);
        contentValues.put(COLUMN_TANGGAL_LAHIR_USER,tanggal_lahir);
        long result = db.insert(TABLE_NAME_USER,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getDataUser(int id_user){
//        DataUserItem dataUserItem=new DataUserItem();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_USER+" where "+COLUMN_ID+" = "+ id_user,null);

        return res;
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataUserItem.Entry.TABLE_NAME_USER,COLUMN_ID+"="+id,null);
    }

    public DataUserItem selectDataUser(int id_user){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor=sqLiteDatabase.query(DataUserItem.Entry.TABLE_NAME_USER,null,COLUMN_ID+"="+id_user,null ,null, null,null);

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ TABLE_NAME_USER+" where "+COLUMN_ID+" = "+ id_user,null);
        cursor.moveToFirst();
//      int id = cursor.getInt(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_ID));

        String name = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NAME_USER));
        String email = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_EMAIL_USER));
        String jk = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_JK_USER));
        String no_telp = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NO_TELP_USER));
        String tanggal_lahir = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_TANGGAL_LAHIR_USER));

        DataUserItem temp = new DataUserItem();
//        temp.setId(id);
        temp.setName(name);
        temp.setEmail(email);
        temp.setJenisKelamin(jk);
        temp.setNoTelp(no_telp);
        temp.setTanggalLahir(tanggal_lahir);

//        int count = cursor.getCount();
//        if (count>0){
//            cursor.moveToFirst();
//            do{
//                int id = cursor.getInt(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_ID));
//                String name = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NAME_USER));
//                String email = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_EMAIL_USER));
//                String jk = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_JK_USER));
//                String no_telp = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NO_TELP_USER));
//                String tanggal_lahir = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_TANGGAL_LAHIR_USER));
//
//                DataUserItem temp = new DataUserItem();
//                temp.setId(id);
//                temp.setName(name);
//                temp.setEmail(email);
//                temp.setJenisKelamin(jk);
//                temp.setNoTelp(no_telp);
//                temp.setTanggalLahir(tanggal_lahir);
//                dataUserItems.add(temp);
//            }while (cursor.moveToNext());
//
//        }
        cursor.close();
        sqLiteDatabase.close();
        return temp;
    }

    //Untuk Tabel Data Jasa
    public boolean insertDataFavorite(int id,int id_user,int id_data_jasa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_ID_USER_FAVORITE,id_user);
        contentValues.put(COLUMN_ID_DATA_JASA_FAVORITE,id_data_jasa);

        long result = db.insert(TABLE_NAME_FAVORITE,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public List<ResponseFavorite> getAllDataFavorite(){
        List<ResponseFavorite> responseFavorites = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor2 = db.rawQuery("select * from "+ TABLE_NAME_FAVORITE,null);

        int count = cursor2.getCount();
        if(count>0){
            cursor2.moveToFirst();
            do{
                int id_favorite = cursor2.getInt(cursor2.getColumnIndex(ResponseFavorite.Entry.COLUMN_ID));
                int id_user = cursor2.getInt(cursor2.getColumnIndex(ResponseFavorite.Entry.COLUMN_ID_USER_FAVORITE));
                int id_data_jasa = cursor2.getInt(cursor2.getColumnIndex(ResponseFavorite.Entry.COLUMN_ID_DATA_JASA_FAVORITE));

                ResponseFavorite temp = new ResponseFavorite();
                temp.setId(id_favorite);
                temp.setIdUser(id_user);
                temp.setIdDataJasa(id_data_jasa);
                responseFavorites.add(temp);

            }while (cursor2.moveToNext());
        }
        cursor2.close();
        db.close();
        return responseFavorites;
    }

    public void deleteFavorite(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ResponseFavorite.Entry.TABLE_NAME_FAVORITE,null,null);
    }

    public int checkFavorite(int id_user,int id_data_jasa){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + TABLE_NAME_FAVORITE + " where " + COLUMN_ID_USER_FAVORITE + " = " + id_user + " and " + COLUMN_ID_DATA_JASA_FAVORITE + " = " + id_data_jasa;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery1,null);
        int jumlah = cursor.getCount();

        return jumlah;
    }

}
