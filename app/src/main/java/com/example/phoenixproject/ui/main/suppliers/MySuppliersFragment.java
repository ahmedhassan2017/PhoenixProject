package com.example.phoenixproject.ui.main.suppliers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phoenixproject.Models.AddSupplierModel;
import com.example.phoenixproject.Models.SupplierModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.OnItemListener;

import java.util.ArrayList;
import java.util.List;

public class MySuppliersFragment extends Fragment implements OnItemListener {
    RecyclerView recyclerView;
    Button addSupplier;
    List<SupplierModel> data = new ArrayList<>();
    sViewModel suppliersViewModel;
    SupplierAdapter adapter;
    String m_name, m_phone, m_state, m_country;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "MySuppliersFragment";

    public MySuppliersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mysuppliers, container, false);


        addSupplier = root.findViewById(R.id.add_sup);


        suppliersViewModel = new ViewModelProvider(this).get(sViewModel.class);


        recyclerView = root.findViewById(R.id.suppliers_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SupplierAdapter(MySuppliersFragment.this);
        recyclerView.setAdapter(adapter);
        getSuppliers();


        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add Supplier");
                // I'm using fragment here so I'm using getView() to provide ViewGroup
                // but you can provide here any other instance of ViewGroup from your Fragment / Activity
                View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.supplier_dialog, (ViewGroup) getView(), false);
                // Set up the input
                final EditText name = viewInflated.findViewById(R.id.dialog_name);
                final EditText phone = viewInflated.findViewById(R.id.dialog_phone);
                final EditText state = viewInflated.findViewById(R.id.dialog_state);
                final EditText country = viewInflated.findViewById(R.id.dialog_country);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                builder.setView(viewInflated);

                // Set up the buttons
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        m_name = name.getText().toString();
                        m_phone = phone.getText().toString();
                        m_state = state.getText().toString();
                        m_country = country.getText().toString();
                        addSupplier();
                        getSuppliers();

                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        return root;
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(getActivity(), SupplierDetailsActivity.class);
        intent.putExtra("id", data.get(position).getId());
        intent.putExtra("userid", data.get(position).getUser_id());
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onDeleteClicked(int position) {


        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Delete Supplier");
        alertDialog.setMessage("Are you sure to delete this supplier?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteSupplier(position, data);


                    }
                });
        alertDialog.show();

    }

    @Override
    public void onEditClicked(int position) {

    }


    public void deleteSupplier(int position, List<SupplierModel> data) {

        suppliersViewModel.deleteSupplier(position, data);
        suppliersViewModel.addSupplierLiveData.observe(getViewLifecycleOwner(), new Observer<AddSupplierModel>() {
            @Override
            public void onChanged(AddSupplierModel addSupplierModel) {
                data.remove(position);
                adapter.notifyItemRemoved(position);

            }
        });

    }

    public void getSuppliers() {
        suppliersViewModel.getSuppliers();
        suppliersViewModel.suppliersLiveData.observe(getViewLifecycleOwner(), new Observer<List<SupplierModel>>() {
            @Override
            public void onChanged(List<SupplierModel> supplierModels) {
                data = supplierModels;
                adapter.setList(supplierModels);

            }
        });

    }

    public void addSupplier() {
        suppliersViewModel.addSupplier(m_name, m_phone, m_state, m_country);
        suppliersViewModel.addSupplierLiveData.observe(getViewLifecycleOwner(), new Observer<AddSupplierModel>() {
            @Override
            public void onChanged(AddSupplierModel addSupplierModel) {

                adapter.notifyDataSetChanged();
            }
        });

    }

}
