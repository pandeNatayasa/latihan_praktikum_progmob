package com.tr.nata.projectandroid.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

public class DataKategoriItem{

	@SerializedName("logo_kategori")
	private String logoKategori;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("kategori")
	private String kategori;

	@SerializedName("id")
	private int id;

	public void setLogoKategori(String logoKategori){
		this.logoKategori = logoKategori;
	}

	public String getLogoKategori(){
		return logoKategori;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setKategori(String kategori){
		this.kategori = kategori;
	}

	public String getKategori(){
		return kategori;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public static class Entry implements BaseColumns {
		public static final String TABLE_NAME_KATEGORI="category_table";
		public static final String COLUMN_ID="ID";
		public static final String COLUMN_KATEGORI="kategori";
		public static final String COLUMN_LOGO_KATEGORI = "logo_kategori";
	}

	@Override
 	public String toString(){
		return 
			"DataKategoriItem{" + 
			"logo_kategori = '" + logoKategori + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",kategori = '" + kategori + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}