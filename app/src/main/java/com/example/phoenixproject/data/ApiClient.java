package com.example.phoenixproject.data;

import com.example.phoenixproject.Models.AddSupplierModel;
import com.example.phoenixproject.Models.DeleteSupplierModel;
import com.example.phoenixproject.Models.LoginModel;
import com.example.phoenixproject.Models.ProductsDatabaseModel;
import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.Models.SupplierModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class ApiClient {

    private static final String BASE_URL = "https://www.yrnova.online/afcrm/";
    private ApiInterface apiInterface;
    private static ApiClient INSTANCE;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiClient getINSTANCE() {
        if (null == INSTANCE)
            INSTANCE = new ApiClient();
        return INSTANCE;
    }

    public Call<LoginModel> doLogin(String action, String phone, String password) {
        return apiInterface.doLogin(action, phone, password);
    }

    public Call<List<SupplierModel>> getSuppliers(String action, String id) {
        return apiInterface.getSuppliers(action, id);
    }

    public Call<SupplierModel> viewSpecificSupplier(String action, String id, String viewID) {
        return apiInterface.viewSpecificSupplier(action, id, viewID);
    }

    public Call<AddSupplierModel> addSupplier(String action, String id, String name, String status, String phone, String city, String country, String loc_long, String loc_lat) {
        return apiInterface.addSupplier(action, id, name, status, phone, city, country, loc_long, loc_lat);
    }

    public Call<DeleteSupplierModel> deleteSupplier(String action,
                                                    String id,
                                                    String supplierid) {
        return apiInterface.deleteSupplier(action, id, supplierid);
    }

    // products
    public Call<List<ProductsModel>> showProducts(String action,
                                                  String id){
        return apiInterface.showProducts(action,id);
    }
    public Call<ProductsDatabaseModel> viewSpecificProduct(String action,
                                                           String uid, String pid){
        return apiInterface.viewSpecificProduct(action,uid,pid);
    }

}
