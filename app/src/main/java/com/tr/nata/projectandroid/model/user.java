package com.tr.nata.projectandroid.model;

import java.util.Date;

public class user {
    private String name;
    private String email;
    private String password;
    private String no_telp;
    private Enum jenis_kelamin;
    private Date tanggal_lahir;

    public user(String name,String email, String password, String no_telp, Enum jenis_kelamin, Date tanggal_lahir){
        this.name=name;
        this.email=email;
        this.password=password;
        this.jenis_kelamin=jenis_kelamin;
        this.tanggal_lahir=tanggal_lahir;
        this.no_telp=no_telp;
    }

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
}
