package com.example.phoenixproject.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SupplierModel {


    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("phone")
    String phone;
    String user_id;
    String city;
    String country;
    String loc_long;
    String loc_lat;
    String address1,notes;
    String created_at;

    public String getNotes() {
        return notes;
    }

    public String getAddress1() {
        return address1;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLoc_long() {
        return loc_long;
    }

    public String getLoc_lat() {
        return loc_lat;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}



