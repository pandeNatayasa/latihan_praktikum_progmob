package com.tr.nata.projectandroid.model;

import com.google.gson.annotations.SerializedName;

public class DataUser{
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

	@SerializedName("status")
	private String status;

	@SerializedName("foto_profille")
	private String foto_profille;

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

	public void setStatusUser(String status){
		this.status = status;
	}

	public String getStatusUser(){
		return status;
	}

	public void setFoto_profille(String foto_profille){
		this.foto_profille = foto_profille;
	}

	public String getFoto_profille(){
		return foto_profille;
	}

	@Override
 	public String toString(){
		return 
			"DataUser{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",no_telp = '" + noTelp + '\'' + 
			",jenis_kelamin = '" + jenisKelamin + '\'' + 
			",email = '" + email + '\'' +
					",foto_profille = '" + foto_profille + '\'' +
			",tanggal_lahir = '" + tanggalLahir + '\'' +
                    ",status = '" + status + '\'' +
			"}";
		}
}
