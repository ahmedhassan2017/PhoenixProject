package com.example.phoenixproject.ui.main.suppliers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoenixproject.Models.SupplierModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.ApiClient;
import com.example.phoenixproject.ui.main.invoice.Invoices_Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierDetailsActivity extends AppCompatActivity {
    SupplierModel supplierData = new SupplierModel();
    TextView name, id, phone, address, city, date, notes;
    Button orderNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_details);
        name = findViewById(R.id.sup_name);
        id = findViewById(R.id.sup_id);
        phone = findViewById(R.id.sup_phone);
        address = findViewById(R.id.sup_address);
        city = findViewById(R.id.sup_city);
        date = findViewById(R.id.sup_date);
        notes = findViewById(R.id.sup_notes);
        orderNow = findViewById(R.id.supplier_order_btn);

        String userid = getIntent().getStringExtra("userid");
        String supplierId = getIntent().getStringExtra("id");
//        int position = getIntent().getIntExtra("position", 0);


        ApiClient.getINSTANCE().viewSpecificSupplier("view", userid, supplierId)
                .enqueue(new Callback<SupplierModel>() {
                    @Override
                    public void onResponse(Call<SupplierModel> call, Response<SupplierModel> response) {
                        supplierData = response.body();
                        name.setText(supplierData.getName());
                        id.setText(supplierData.getId());
                        phone.setText(supplierData.getPhone());
//                        address.setText(data.getAddress1());
                        city.setText(supplierData.getCity());
                        date.setText(supplierData.getCreated_at());
//                        notes.setText(data.getNotes());

                    }

                    @Override
                    public void onFailure(Call<SupplierModel> call, Throwable t) {
                        Toast.makeText(SupplierDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SupplierDetailsActivity.this, Invoices_Activity.class);
                i.putExtra("name",supplierData.getName());
                i.putExtra("phone",supplierData.getPhone());
                i.putExtra("address",supplierData.getCity());
                i.putExtra("id",supplierData.getId());
                i.putExtra("invoiceType","Supplier Invoice");
                startActivity(i);
            }
        });
    }
}