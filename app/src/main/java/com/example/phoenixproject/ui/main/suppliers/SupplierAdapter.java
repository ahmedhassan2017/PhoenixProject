package com.example.phoenixproject.ui.main.suppliers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phoenixproject.Models.SupplierModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.OnItemListener;

import java.util.ArrayList;
import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierAdapterViewHolder> {

    List<SupplierModel> SupplierModelArrayList = new ArrayList<>();
    private OnItemListener onItemListener;

    public SupplierAdapter(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public SupplierAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SupplierAdapterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.supplier_item, parent, false), onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierAdapterViewHolder holder, int position) {

        holder.name.setText(SupplierModelArrayList.get(position).getName());
        holder.phone.setText(SupplierModelArrayList.get(position).getPhone());
        holder.id.setText(SupplierModelArrayList.get(position).getId());
        holder.country.setText(SupplierModelArrayList.get(position).getCountry());
        holder.city.setText(SupplierModelArrayList.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return SupplierModelArrayList.size();
    }

    public void setList(List<SupplierModel> moviesList) {
        SupplierModelArrayList = moviesList;
        notifyDataSetChanged();
    }


    public class SupplierAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, phone, id, country, city;
        OnItemListener onItemListener;
        ImageView removeImage;

        public SupplierAdapterViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            name = itemView.findViewById(R.id.supplier_name);
            phone = itemView.findViewById(R.id.supplier_phone);
            id = itemView.findViewById(R.id.supplier_id);
            country = itemView.findViewById(R.id.supplier_country);
            city = itemView.findViewById(R.id.supplier_city);
            removeImage = itemView.findViewById(R.id.cart_remove_image);
            this.onItemListener = onItemListener;
            removeImage.setOnClickListener(this);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (v.equals(removeImage)) {
                onItemListener.onDeleteClicked(getAdapterPosition());
            } else
                onItemListener.onItemClicked(getAdapterPosition());
        }
    }


}
