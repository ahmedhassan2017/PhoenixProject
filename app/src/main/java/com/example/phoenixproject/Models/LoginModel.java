package com.example.phoenixproject.Models;


import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("reply")
    String reply;

    public String getReply() {
        return reply;
    }

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
