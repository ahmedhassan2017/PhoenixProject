package com.example.phoenixproject.ui.main.invoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoenixproject.Models.ProductsDatabaseModel;
import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.OnItemListener;
import com.example.phoenixproject.data.ProductsDatabase;
import com.example.phoenixproject.ui.main.products.ProductDetailes;
import com.example.phoenixproject.ui.main.products.ProductsActivity;
import com.example.phoenixproject.ui.main.products.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Invoices_Activity extends AppCompatActivity implements OnItemListener {
    Button addProduct;
    TextView clientOrSupplierName, clientOrSupplierPhone, clientOrSupplierAddress, clientOrSupplierID, totalOfTotals, invoiceTypeTV;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<ProductsDatabaseModel> products = new ArrayList<>();
    InvoiceAdapter adapter;
    ProductsDatabase productsDatabase;
    private static final String TAG = "Invoices_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices);
        recyclerView = findViewById(R.id.invoices_cart_recycler);
        addProduct = findViewById(R.id.add_product);
        clientOrSupplierName = findViewById(R.id.invoice_user_name);
        clientOrSupplierPhone = findViewById(R.id.invoice_user_phone);
        clientOrSupplierAddress = findViewById(R.id.invoice_user_address);
//        clientOrSupplierID = findViewById(R.id.invoice_user_id);
        totalOfTotals = findViewById(R.id.total_of_totals);
        invoiceTypeTV = findViewById(R.id.invoice_type);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");
        String id = getIntent().getStringExtra("id");
        String invoiceType = getIntent().getStringExtra("invoiceType");

//        clientOrSupplierID.setText(id);
        clientOrSupplierPhone.setText(phone);
        clientOrSupplierAddress.setText(address);
        clientOrSupplierName.setText(name);
        invoiceTypeTV.setText(invoiceType);


        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Invoices_Activity.this, ProductsActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InvoiceAdapter(this);
        recyclerView.setAdapter(adapter);
        // here i will get data
        productsDatabase = ProductsDatabase.getINSTANCE(Invoices_Activity.this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        getProducts();

    }

    private void countTotal() {
        int totalAll = 0;
        for (int i = 0; i < products.size(); i++) {
            totalAll += products.get(i).getTotal();
        }
        totalOfTotals.setText(String.valueOf(totalAll));
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(Invoices_Activity.this, ProductDetailes.class);
        intent.putExtra("pid", products.get(position).getPid());
        intent.putExtra("UID", products.get(position).getSupplier_id());
        intent.putExtra("comeFrom", "activity");
        startActivity(intent);
    }

    @Override
    public void onDeleteClicked(int position) {

        deleteProduct(position);
    }

    private void getProducts() {
        productsDatabase.productsDao().getProducts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<ProductsDatabaseModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<ProductsDatabaseModel> productsDatabaseModels) {
                Log.d(TAG, "Ahmed onSuccess: ");
                products = productsDatabaseModels;
                adapter.setList(products);
                adapter.notifyDataSetChanged();
                countTotal();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Ahmed onError: " + e.getMessage());
            }
        });

    }


    private void deleteProduct(int position) {

        productsDatabase.productsDao().deleteProduct(products.get(position).getPid())
                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "AhmedDelete onComplete: ");
                        products.remove(position);
                        adapter.notifyItemRemoved(position);
                        countTotal();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "AhmedDelete onError: ");
                    }
                });
    }

    @Override
    public void onEditClicked(int position) {

    }
}