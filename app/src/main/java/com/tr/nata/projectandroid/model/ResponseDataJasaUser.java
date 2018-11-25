package com.tr.nata.projectandroid.model;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ResponseDataJasaUser{

	@SerializedName("usia")
	private int usia;

	@SerializedName("id_kategori")
	private int idKategori;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("status_validasi")
	private String statusValidasi;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("id_user_admin")
	private int idUserAdmin;

	@SerializedName("estimasi_gaji")
	private int estimasiGaji;

	@SerializedName("pekerjaan")
	private String pekerjaan;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("pengalaman_kerja")
	private String pengalamanKerja;

	@SerializedName("id")
	private int id;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
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

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setStatusValidasi(String statusValidasi){
		this.statusValidasi = statusValidasi;
	}

	public String getStatusValidasi(){
		return statusValidasi;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setIdUserAdmin(int idUserAdmin){
		this.idUserAdmin = idUserAdmin;
	}

	public int getIdUserAdmin(){
		return idUserAdmin;
	}

	public void setEstimasiGaji(int estimasiGaji){
		this.estimasiGaji = estimasiGaji;
	}

	public int getEstimasiGaji(){
		return estimasiGaji;
	}

	public void setPekerjaan(String pekerjaan){
		this.pekerjaan = pekerjaan;
	}

	public String getPekerjaan(){
		return pekerjaan;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPengalamanKerja(String pengalamanKerja){
		this.pengalamanKerja = pengalamanKerja;
	}

	public String getPengalamanKerja(){
		return pengalamanKerja;
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
			"ResponseDataJasaUser{" + 
			"usia = '" + usia + '\'' + 
			",id_kategori = '" + idKategori + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",status_validasi = '" + statusValidasi + '\'' + 
			",alamat = '" + alamat + '\'' +
			",id_user_admin = '" + idUserAdmin + '\'' + 
			",estimasi_gaji = '" + estimasiGaji + '\'' + 
			",pekerjaan = '" + pekerjaan + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",pengalaman_kerja = '" + pengalamanKerja + '\'' + 
			",id = '" + id + '\'' + 
			",no_telp = '" + noTelp + '\'' + 
			",email = '" + email + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}