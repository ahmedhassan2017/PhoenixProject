package com.example.phoenixproject.ui.main.products;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.ApiClient;
import com.example.phoenixproject.data.OnItemListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyProductsFragment extends Fragment implements OnItemListener {
    List<ProductsModel> productsList = new ArrayList<>();
    private RecyclerView recyclerView;
    ProductsAdapter adapter;
    pViewModel productsViewModel;

    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "MyProductsFragment";


    public MyProductsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myproducts, container, false);
        recyclerView = root.findViewById(R.id.products_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),
                2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(MyProductsFragment.this, R.layout.product_edit_item);
        recyclerView.setAdapter(adapter);

        productsViewModel = new ViewModelProvider(this).get(pViewModel.class);

        showProducts("show", "1");

        return root;
    }

    private void showProducts(String action, String id) {

        productsViewModel.showProducts(action, id);
        productsViewModel.productsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<ProductsModel>>() {
            @Override
            public void onChanged(List<ProductsModel> productsModels) {
                productsList = productsModels;
                adapter.setList(productsModels);
            }
        });
    }


    @Override
    public void onItemClicked(int position) {

        Intent intent = new Intent(getActivity(), ProductDetailes.class);
        intent.putExtra("comeFrom", "fragment");
        intent.putExtra("UID", productsList.get(position).getSupplier_id());
        intent.putExtra("pid", productsList.get(position).getPid());

        startActivity(intent);
    }

    @Override
    public void onDeleteClicked(int position) {

    }

    @Override
    public void onEditClicked(int position) {
        Log.d(TAG, "onEditClicked :  " + position);
    }
}
