package com.example.phoenixproject.ui.main.login;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phoenixproject.Models.LoginModel;
import com.example.phoenixproject.Models.User;
import com.example.phoenixproject.data.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginViewModel extends ViewModel {

    MutableLiveData<LoginModel> userMutableLiveData = new MutableLiveData<>();


    public void doLogin(String action, String phone, String password) {
        ApiClient.getINSTANCE().doLogin(action, phone, password)
                .enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                userMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }

}
