package com.tr.nata.projectandroid.model;

import com.google.gson.annotations.SerializedName;

import java.time.DateTimeException;
import java.util.Date;

public class user {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("no_telp")
    private String no_telp;

    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("jenis_kelamin")
    private Enum jenis_kelamin;

    @SerializedName("tanggal_lahir")
    private Date tanggal_lahir;

//    public user(int id, String name,String email,Enum jenis_kelamin, String no_telp, Date tanggal_lahir, String created_at,String updated_at){
//        this.id=id;
//        this.name=name;
//        this.email=email;
//        this.jenis_kelamin=jenis_kelamin;
//        this.tanggal_lahir=tanggal_lahir;
//        this.no_telp=no_telp;
//        this.created_at=created_at;
//        this.updated_at=updated_at;
//    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp){
        this.no_telp=no_telp;
    }

    public Enum getJenisKelamin() {
        return jenis_kelamin;
    }

    public void setJenisKelamin(Enum jenisKelamin){
        this.jenis_kelamin=jenisKelamin;
    }

    public Date getTanggalLahir() {
        return tanggal_lahir;
    }

    public void setTanggalLahir(Date tanggalLahir){
        this.tanggal_lahir=tanggalLahir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at){
        this.created_at=created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at){
        this.updated_at=updated_at;
    }


    @Override
    public String toString(){
        return
                "User{" +
                        ",id = '" + id + '\'' +
                        ",name = '" + name + '\'' +
                        ",email = '" + email + '\'' +
                        ",jenis_kelamin = '" + jenis_kelamin + '\'' +
                        ",no_telp = '" + no_telp + '\'' +
                        ",tanggal_lahir = '" + tanggal_lahir + '\'' +
                        ",updated_at = '" + updated_at + '\'' +
                        ",created_at = '" + created_at + '\'' +
                        "}";
    }
}
