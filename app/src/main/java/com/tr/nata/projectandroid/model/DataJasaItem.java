package com.tr.nata.projectandroid.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

public class DataJasaItem{
	@SerializedName("usia")
	private int usia;

	@SerializedName("id_kategori")
	private int idKategori;

	@SerializedName("id_user_admin")
	private int idAdmin;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("alamat")
	private String alamat;


	@SerializedName("pekerjaan")
	private String pekerjaan;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

    @SerializedName("estimasi_gaji")
    private int estimasi_gaji;

    @SerializedName("pengalaman_kerja")
    private String pengalaman_kerja;

	public void setUsia(int usia){
		this.usia = usia;
	}

	public int getUsia(){
		return usia;
	}

	public void setIdKategori(int idKategori){
		this.idKategori = idKategori;
	}

	public int getIdKategori(){
		return idKategori;
	}

	public void setIdAdmin(int idAdmin){
		this.idAdmin = idAdmin;
	}

	public int getIdAdmin(){
		return idAdmin;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setPekerjaan(String pekerjaan){
		this.pekerjaan = pekerjaan;
	}

	public String getPekerjaan(){
		return pekerjaan;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

    public void setEstimasi_gaji(int estimasi_gaji){
        this.estimasi_gaji=estimasi_gaji;
    }

    public Integer getEstimasi_gaji(){
        return estimasi_gaji;
    }

    public void setPengalaman_kerja(String pengalaman_kerja){
        this.pengalaman_kerja=pengalaman_kerja;
    }

    public String getPengalaman_kerja(){
        return pengalaman_kerja;
    }

	public static class Entry implements BaseColumns {
		public static final String TABLE_NAME_JASA="data_jasa_table";
		public static final String COLUMN_ID="ID";
		public static final String COLUMN_ID_KATEGORI="id_kategori";
		public static final String COLUMN_ID_USER="id_user";
		public static final String COLUMN_PEKERJAAN="pekerjaan";
		public static final String COLUMN_ESTIMASI_GAJI="estimasi_gaji";
		public static final String COLUMN_USIA="usia";
		public static final String COLUMN_NO_TELP="no_telp";
		public static final String COLUMN_EMAIL_JASA="email_jasa";
		public static final String COLUMN_STATUS="status";
		public static final String COLUMN_ALAMAT_JASA="alamat";
		public static final String COLUMN_PENGALAMAN_KERJA="pengalaman_kerja";
	}

	@Override
 	public String toString(){
		return 
			"DataJasaItem{" + 
			"usia = '" + usia + '\'' + 
			",id_kategori = '" + idKategori + '\'' + 
			",id_admin = '" + idAdmin + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",alamat = '" + alamat + '\'' +
			",pekerjaan = '" + pekerjaan + '\'' +
                    ",estimasi_gaji = '" + estimasi_gaji + '\'' +
                    ",pengalaman_kerja = '" + pengalaman_kerja + '\'' +
                    ",updated_at = '" + updatedAt + '\'' +
			",id = '" + id + '\'' + 
			",no_telp = '" + noTelp + '\'' + 
			",email = '" + email + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
