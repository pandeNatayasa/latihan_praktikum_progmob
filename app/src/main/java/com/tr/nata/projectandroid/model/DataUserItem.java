package com.tr.nata.projectandroid.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

public class DataUserItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("email")
	private String email;

	@SerializedName("tanggal_lahir")
	private String tanggalLahir;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
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

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setTanggalLahir(String tanggalLahir){
		this.tanggalLahir = tanggalLahir;
	}

	public String getTanggalLahir(){
		return tanggalLahir;
	}

	public static class Entry implements BaseColumns{
		public static final String TABLE_NAME_USER="data_user_table";
		public static final String COLUMN_ID="id";
		public static final String COLUMN_NAME_USER="name";
		public static final String COLUMN_EMAIL_USER="email";
		public static final String COLUMN_JK_USER="jenis_kelamin";
		public static final String COLUMN_NO_TELP_USER="no_telp";
		public static final String COLUMN_TANGGAL_LAHIR_USER="tanggal_lahir";
	}

	@Override
 	public String toString(){
		return 
			"DataUserItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",no_telp = '" + noTelp + '\'' + 
			",jenis_kelamin = '" + jenisKelamin + '\'' + 
			",email = '" + email + '\'' + 
			",tanggal_lahir = '" + tanggalLahir + '\'' + 
			"}";
		}
}
