package com.tr.nata.projectandroid.model;

import com.google.gson.annotations.SerializedName;

public class ResponseChekFavorite{

	@SerializedName("jumlah_favorite")
	private int jumlahFavorite;

	public void setJumlahFavorite(int jumlahFavorite){
		this.jumlahFavorite = jumlahFavorite;
	}

	public int getJumlahFavorite(){
		return jumlahFavorite;
	}

	@Override
 	public String toString(){
		return 
			"ResponseChekFavorite{" + 
			"jumlah_favorite = '" + jumlahFavorite + '\'' + 
			"}";
		}
}
