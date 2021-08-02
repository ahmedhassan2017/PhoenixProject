package com.example.phoenixproject.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "products_table")
public class ProductsDatabaseModel {
    @PrimaryKey(autoGenerate = true)
    private int roomID;
    @SerializedName("id")
    private String pid;
    @SerializedName("client_id")
    String supplier_id;
    String name, img, cat_id, desc, price_in, price_out, barcode;
    String quantity ;
    int total;


    public int getRoomID() {
        return roomID;
    }

    public ProductsDatabaseModel() {

    }

    public ProductsDatabaseModel(String pid, String name, String img, String supplier_id, String cat_id, String desc, String price_in, String price_out, String barcode) {
        this.pid = pid;
        this.name = name;
        this.img = img;
        this.supplier_id = supplier_id;
        this.cat_id = cat_id;
        this.desc = desc;
        this.price_in = price_in;
        this.price_out = price_out;
        this.barcode = barcode;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

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


    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice_in(String price_in) {
        this.price_in = price_in;
    }

    public void setPrice_out(String price_out) {
        this.price_out = price_out;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
