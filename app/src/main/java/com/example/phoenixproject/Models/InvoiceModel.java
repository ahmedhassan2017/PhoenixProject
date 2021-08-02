package com.example.phoenixproject.Models;

public class InvoiceModel {
    String productname,
            quantity,
            taxes,
            price,
            totalPrice,
            image;

    public String getImage() {
        return image;
    }

    public String getProductname() {
        return productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTaxes() {
        return taxes;
    }

    public String getPrice() {
        return price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
