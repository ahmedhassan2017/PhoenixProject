package com.example.phoenixproject.ui.main.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.ApiClient;
import com.example.phoenixproject.data.OnItemListener;
import com.example.phoenixproject.ui.ScanActivity;
import com.example.phoenixproject.ui.main.suppliers.sViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity implements OnItemListener {
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<ProductsModel> products = new ArrayList<>();
    ProductsAdapter adapter;
    pViewModel productsViewModel;
    private static final String TAG = "ProductsActivity";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView = findViewById(R.id.product_list);
        toolbar = findViewById(R.id.mytoolbar);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(ProductsActivity.this,
                2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(ProductsActivity.this, R.layout.product_item);
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);

        productsViewModel = new ViewModelProvider(this).get(pViewModel.class);
        showProducts("show", "1");




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.scan) {
            Intent intent = new Intent(ProductsActivity.this, ScanActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    private void showProducts(String action, String id) {
        productsViewModel.showProducts(action, id);
        productsViewModel.productsMutableLiveData.observe(this, new Observer<List<ProductsModel>>() {
            @Override
            public void onChanged(List<ProductsModel> productsModels) {
                products = productsModels;
                adapter.setList(productsModels);
            }
        });

    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(ProductsActivity.this, ProductDetailes.class);
        intent.putExtra("pid", products.get(position).getPid());
        intent.putExtra("UID", products.get(position).getSupplier_id());
        intent.putExtra("comeFrom", "activity");


        startActivity(intent);
    }

    @Override
    public void onDeleteClicked(int position) {

    }

    @Override
    public void onEditClicked(int position) {

    }
}