package com.example.phoenixproject.ui.main.invoice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phoenixproject.Models.InvoiceModel;
import com.example.phoenixproject.Models.ProductsDatabaseModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.OnItemListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    List<ProductsDatabaseModel> InvoiceArrayList = new ArrayList<>();
    private OnItemListener onInvoiceListener;



    public InvoiceAdapter(OnItemListener onInvoiceListener) {
        this.onInvoiceListener = onInvoiceListener;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InvoiceViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoices_item, parent, false), onInvoiceListener);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {


        holder.pName.setText(InvoiceArrayList.get(position).getName());
        holder.Price.setText(InvoiceArrayList.get(position).getPrice_in());
        holder.quantity.setText(InvoiceArrayList.get(position).getQuantity());
        holder.totalPrice.setText(String.valueOf(InvoiceArrayList.get(position).getTotal()));
        Picasso.get().load(InvoiceArrayList.get(position).getImg()).into(holder.pImage);


    }

    @Override
    public int getItemCount() {
        return InvoiceArrayList.size();
    }

    public void setList(List<ProductsDatabaseModel> cartsList) {
        InvoiceArrayList = cartsList;
        notifyDataSetChanged();
    }

    public class InvoiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pName, Price, quantity, taxes,totalPrice;
        ImageView pImage;
        ImageView removeImage;
        OnItemListener onInvoiceListener;

        public InvoiceViewHolder(@NonNull View itemView, OnItemListener onInvoiceListener) {
            super(itemView);

            pName = itemView.findViewById(R.id.cart_product_name);
            Price = itemView.findViewById(R.id.cart_product_price);
            pImage = itemView.findViewById(R.id.product_image_cart);
            quantity = itemView.findViewById(R.id.cart_product_quantity);
            taxes =itemView.findViewById(R.id.cart_product_taxes);
            totalPrice = itemView.findViewById(R.id.cart_product_totalprice);
            removeImage = itemView.findViewById(R.id.cart_remove_image);
            this.onInvoiceListener = onInvoiceListener;
            removeImage.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.equals(removeImage)) {
                onInvoiceListener.onDeleteClicked(getAdapterPosition());
            }else
                onInvoiceListener.onItemClicked(getAdapterPosition());
        }
    }


}

