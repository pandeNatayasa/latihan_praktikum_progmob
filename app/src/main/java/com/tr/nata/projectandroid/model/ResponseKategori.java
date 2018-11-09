package com.tr.nata.projectandroid.model;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ResponseKategori{

	@SerializedName("dataKategori")
	private List<DataKategoriItem> dataKategori;

	public void setDataKategori(List<DataKategoriItem> dataKategori){
		this.dataKategori = dataKategori;
	}

	public List<DataKategoriItem> getDataKategori(){
		return dataKategori;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKategori{" + 
			"dataKategori = '" + dataKategori + '\'' + 
			"}";
		}
}