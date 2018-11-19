package com.tr.nata.projectandroid.model;

//import javax.annotation.Generated;
import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class DataKategoriItem{

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("kategori")
	private String kategori;

	@SerializedName("id")
	private int id;

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public DataKategoriItem setKategori(String kategori){
		this.kategori = kategori;
		return null;
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

	public static class Entry implements BaseColumns{
		public static final String TABLE_NAME_KATEGORI="category_table";
		public static final String COLUMN_ID="id";
		public static final String COLUMN_KATEGORI="kategori";
	}

	@Override
 	public String toString(){
		return 
			"DataKategoriItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",kategori = '" + kategori + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}