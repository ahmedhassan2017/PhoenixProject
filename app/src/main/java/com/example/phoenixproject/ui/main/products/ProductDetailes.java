package com.example.phoenixproject.ui.main.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoenixproject.Models.ProductsDatabaseModel;
import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.ApiClient;
import com.example.phoenixproject.data.ProductsDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailes extends AppCompatActivity {
    TextView desc, name, priceIn, priceOut, barcode;
    ImageView image;
    Button plus, minus, addToInvoice;
    TextView quantity, qntSahpe;
    LinearLayout elegant;
    ProductsDatabase productsDatabase;
    private static final String TAG = "ProductDetailes";
    ProductsDatabaseModel productDetails = new ProductsDatabaseModel();
    pViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailes);
        String uid = getIntent().getStringExtra("UID");
        String pid = getIntent().getStringExtra("pid");
        priceIn = findViewById(R.id.product_price_in_details);
        name = findViewById(R.id.product_name_details);
        desc = findViewById(R.id.product_description_details);
        image = findViewById(R.id.product_image_details);
        priceOut = findViewById(R.id.product_price_out_details);
        barcode = findViewById(R.id.product_barcode_details);
        addToInvoice = findViewById(R.id.add_product_to_InvoiceCart);
        qntSahpe = findViewById(R.id.quantityShape);
        elegant = findViewById(R.id.elegantlin);

        String comeFrom = getIntent().getStringExtra("comeFrom");

        addToInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 productsDatabase = ProductsDatabase.getINSTANCE(ProductDetailes.this);
                productDetails.setQuantity(quantity.getText().toString());
                int total = Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(productDetails.getPrice_in());
                productDetails.setTotal(total);

                insertProduct();
            }

        });


        if (comeFrom.equals("fragment")) {
            elegant.setVisibility(View.GONE);
            addToInvoice.setVisibility(View.GONE);
            qntSahpe.setVisibility(View.GONE);

        } else if (comeFrom.equals("activity")) {
            elegant.setVisibility(View.VISIBLE);
            addToInvoice.setVisibility(View.VISIBLE);
            qntSahpe.setVisibility(View.VISIBLE);
        }


        plus = findViewById(R.id.plusBtn);
        minus = findViewById(R.id.minusBtn);
        quantity = findViewById(R.id.itemQuanEt);
        plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doPlus();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(quantity.getText().toString()) > 0)
                    doMinus();
            }
        });

        viewModel = new ViewModelProvider(this).get(pViewModel.class);

        getSpecificProduct("view", uid, pid);

    }

    private void insertProduct() {
        productsDatabase.productsDao().insertProduct(productDetails)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: " + "done");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });


    }


    private void getSpecificProduct(String action, String uid, String pid) {
        viewModel.getSpecificProduct(action, uid, pid);
        viewModel.specificProductMutableLiveData.observe(this, new Observer<ProductsDatabaseModel>() {
            @Override
            public void onChanged(ProductsDatabaseModel productsModel) {
                productDetails = productsModel;
                priceIn.setText(productsModel.getPrice_in());
                name.setText(productsModel.getName());
                desc.setText(productsModel.getDesc());
                priceOut.setText(productsModel.getPrice_out());
                barcode.setText(productsModel.getBarcode());
                Picasso.get().load(productsModel.getImg()).into(image);

            }
        });
    }

    private void doMinus() {

        display(Integer.parseInt(quantity.getText().toString()) - 1);
    }

    private void doPlus() {
        display(Integer.parseInt(quantity.getText().toString()) + 1);
    }

    private void display(int number) {
        quantity.setText(String.valueOf(number));
    }

}