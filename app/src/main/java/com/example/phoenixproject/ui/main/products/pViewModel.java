package com.example.phoenixproject.ui.main.products;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.phoenixproject.Models.ProductsDatabaseModel;
import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pViewModel extends ViewModel {

    MutableLiveData <List<ProductsModel>> productsMutableLiveData = new MutableLiveData<>();
    MutableLiveData <ProductsDatabaseModel> specificProductMutableLiveData = new MutableLiveData<>();

    public void showProducts(String action, String id) {

        ApiClient.getINSTANCE().showProducts(action, id).enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {

                productsMutableLiveData.setValue(response.body());
//                Log.d(TAG, "onResponse: " + response.body().get(0).getName());

            }

            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public  void getSpecificProduct(String action, String uid, String pid){
        ApiClient.getINSTANCE().viewSpecificProduct(action, uid, pid).enqueue(new Callback<ProductsDatabaseModel>() {
            @Override
            public void onResponse(Call<ProductsDatabaseModel> call, Response<ProductsDatabaseModel> response) {
                if (response.body() != null) {
                    specificProductMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<ProductsDatabaseModel> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
