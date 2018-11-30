package com.tr.nata.projectandroid.model;

import com.google.gson.annotations.SerializedName;

public class ResponseFavorite{

	@SerializedName("id_data_jasa")
	private int idDataJasa;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("id_user")
	private int idUser;

	public void setIdDataJasa(int idDataJasa){
		this.idDataJasa = idDataJasa;
	}

	public int getIdDataJasa(){
		return idDataJasa;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	@Override
 	public String toString(){
		return 
			"ResponseFavorite{" + 
			"id_data_jasa = '" + idDataJasa + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			"}";
		}
}