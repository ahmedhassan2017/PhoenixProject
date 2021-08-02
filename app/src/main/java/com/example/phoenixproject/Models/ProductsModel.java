package com.example.phoenixproject.Models;

import com.google.gson.annotations.SerializedName;

public class ProductsModel {
    @SerializedName("id")
    String pid;
    String name,img;
    @SerializedName("client_id")
    String supplier_id;

    String cat_id,desc,price_in,price_out,barcode;

    public String getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice_in() {
        return price_in;
    }

    public String getPrice_out() {
        return price_out;
    }

    public String getBarcode() {
        return barcode;
    }
}
