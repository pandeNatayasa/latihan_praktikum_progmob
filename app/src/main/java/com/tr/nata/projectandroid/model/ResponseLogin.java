package com.tr.nata.projectandroid.model;


import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("dataUser")
	private DataUser dataUser;

	@SerializedName("token")
	private String token;

	@SerializedName("status")
	private boolean status;

	public void setDataUser(DataUser dataUser){
		this.dataUser = dataUser;
	}

	public DataUser getDataUser(){
		return dataUser;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
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
			"ResponseLogin{" + 
			"dataUser = '" + dataUser + '\'' + 
			",token = '" + token + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}