package com.tr.nata.projectandroid.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseStoreKategori{

	@SerializedName("dataKategori")
	private List<DataKategoriItem> dataKategori;

	@SerializedName("status")
	private boolean status;

	public void setDataKategori(List<DataKategoriItem> dataKategori){
		this.dataKategori = dataKategori;
	}

	public List<DataKategoriItem> getDataKategori(){
		return dataKategori;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseStoreKategori{" + 
			"dataKategori = '" + dataKategori + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}