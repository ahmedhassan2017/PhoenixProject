package com.example.phoenixproject.data;

import android.icu.text.StringPrepParseException;

import com.example.phoenixproject.Models.AddSupplierModel;
import com.example.phoenixproject.Models.DeleteSupplierModel;
import com.example.phoenixproject.Models.LoginModel;
import com.example.phoenixproject.Models.ProductsDatabaseModel;
import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.Models.SupplierModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //http://yrnova.online/afcrm/index.php?action=login&phone=01063052494&password=123456
    @GET("index.php")
    public Call<LoginModel> doLogin(@Query("action") String action,
                                    @Query("phone") String phone,
                                    @Query("password") String password);


    // suppliers Apis
    @GET("suppliers.php")
    public Call<List<SupplierModel>> getSuppliers(@Query("action") String action,
                                                  @Query("id") String id);

    @GET("suppliers.php")
    public Call<SupplierModel> viewSpecificSupplier(@Query("action") String action,
                                                    @Query("id") String id,
                                                    @Query("viewid") String viewID);

    @GET("suppliers.php")
    public Call<AddSupplierModel> addSupplier(@Query("action") String action,
                                              @Query("id") String id,
                                              @Query("name") String name,
                                              @Query("status") String status,
                                              @Query("phone") String phone,
                                              @Query("city") String city,
                                              @Query("country") String country,
                                              @Query("loc_long") String loc_long,
                                              @Query("loc_lat") String loc_lat);

    //  action=add&id=5&name=ali&status=new&phone=01564156&city=cairo&country=Egypt&loc_long=20.8556&loc_lat=31.85456
    @GET("suppliers.php")
    public Call<DeleteSupplierModel> deleteSupplier(@Query("action") String action,
                                                    @Query("id") String id,
                                                    @Query("supplierid") String supplierid);


// products APIs

    @GET("products.php")
    public Call<List<ProductsModel>> showProducts(@Query("action") String action,
                                                  @Query("uid") String id);

    @GET("products.php")
    public Call<ProductsDatabaseModel> viewSpecificProduct(@Query("action") String action,
                                                           @Query("uid") String uid, @Query("pid") String pid);


//    public Call<LoginModel> doLogin(@Body HashMap<Object, Object> map);


}
