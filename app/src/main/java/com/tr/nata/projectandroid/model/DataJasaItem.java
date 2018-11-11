package com.tr.nata.projectandroid.model;

public class DataJasaItem{
	private int usia;
	private int idKategori;
	private int idAdmin;
	private Object createdAt;
	private int idUser;
	private String alamat;
	private int idKecamatan;
	private String pekerjaan;
	private Object updatedAt;
	private int id;
	private String noTelp;
	private String email;
	private String status;


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

	public void setIdKecamatan(int idKecamatan){
		this.idKecamatan = idKecamatan;
	}

	public int getIdKecamatan(){
		return idKecamatan;
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
			",id_kecamatan = '" + idKecamatan + '\'' + 
			",pekerjaan = '" + pekerjaan + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",id = '" + id + '\'' + 
			",no_telp = '" + noTelp + '\'' + 
			",email = '" + email + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
