package com.tr.nata.projectandroid.model;

import java.util.List;

public class ResponseDataJasa{
	private List<DataJasaItem> dataJasa;
	private List<DataUserItem> dataUser;

	public void setDataJasa(List<DataJasaItem> dataJasa){
		this.dataJasa = dataJasa;
	}

	public List<DataJasaItem> getDataJasa(){
		return dataJasa;
	}

	public void setDataUser(List<DataUserItem> dataUser){
		this.dataUser = dataUser;
	}

	public List<DataUserItem> getDataUser(){
		return dataUser;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDataJasa{" + 
			"dataJasa = '" + dataJasa + '\'' + 
			",dataUser = '" + dataUser + '\'' + 
			"}";
		}
}