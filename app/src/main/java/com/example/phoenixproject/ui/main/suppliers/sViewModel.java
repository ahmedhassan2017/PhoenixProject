package com.example.phoenixproject.ui.main.suppliers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phoenixproject.Models.AddSupplierModel;
import com.example.phoenixproject.Models.DeleteSupplierModel;
import com.example.phoenixproject.Models.SupplierModel;
import com.example.phoenixproject.data.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sViewModel extends ViewModel {

    MutableLiveData<List<SupplierModel>> suppliersLiveData = new MutableLiveData<>();
    MutableLiveData <AddSupplierModel> addSupplierLiveData = new MutableLiveData<>();
    MutableLiveData<DeleteSupplierModel> deleteSupplierLiveData = new MutableLiveData<>();

    public void getSuppliers() {

        ApiClient.getINSTANCE().getSuppliers("show", "5")
                .enqueue(new Callback<List<SupplierModel>>() {
                    @Override
                    public void onResponse(Call<List<SupplierModel>> call, Response<List<SupplierModel>> response) {
                        suppliersLiveData.setValue( response.body());

                    }

                    @Override
                    public void onFailure(Call<List<SupplierModel>> call, Throwable t) {
                    }
                });

    }

    public void addSupplier(String m_name , String m_phone ,String m_state,String m_country ) {
        ApiClient.getINSTANCE().addSupplier("add", "5", m_name, "new",
                m_phone, m_state, m_country, "14.5526", "15.2585").enqueue(new Callback<AddSupplierModel>() {
            @Override
            public void onResponse(Call<AddSupplierModel> call, Response<AddSupplierModel> response) {
//                adapter.notifyDataSetChanged();
                addSupplierLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AddSupplierModel> call, Throwable t) {

            }
        });

    }


    public void deleteSupplier(int position, List<SupplierModel> data) {

        ApiClient.getINSTANCE().deleteSupplier("delete", data.get(position).getUser_id(), data.get(position).getId()).enqueue(new Callback<DeleteSupplierModel>() {
            @Override
            public void onResponse(Call<DeleteSupplierModel> call, Response<DeleteSupplierModel> response) {
//                Log.d(TAG, "onResponse: " + response.body().getReply());
//                data.remove(position);
//                adapter.notifyItemRemoved(position);
                deleteSupplierLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DeleteSupplierModel> call, Throwable t) {

            }
        });
    }
}
