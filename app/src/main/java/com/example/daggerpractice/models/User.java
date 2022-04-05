package com.example.daggerpractice.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("username")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    public User(){}
    public User(int id,String username,String email,String website)
    {
        this.id=id;
        this.userName=username;
        this.email=email;
        this.website=website;
    }
    public void setId(int num){
        this.id=num;
    }
    public int getId()
    {return id;}
    public String getEmail()
    { return email; }
}
