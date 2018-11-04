package com.tr.nata.projectandroid.model;

public class userLogin {

    private boolean status;
    private String email;
    private String password;

    public userLogin(String email,String password, boolean status){

        this.status=status;
        this.email=email;
        this.password=password;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status=status;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }
}
