package com.example.phoenixproject.data;

import android.database.Observable;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.phoenixproject.Models.InvoiceModel;
import com.example.phoenixproject.Models.ProductsDatabaseModel;
import com.example.phoenixproject.Models.ProductsModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ProductsDao {
    @Insert
    Completable insertProduct(ProductsDatabaseModel productsDatabaseModel);

    @Query("select * from products_table")
    Single<List<ProductsDatabaseModel>> getProducts();

    @Query("delete from products_table where pid = :productID")
    Completable  deleteProduct(String productID);




}

