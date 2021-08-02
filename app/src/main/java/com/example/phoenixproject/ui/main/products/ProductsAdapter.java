package com.example.phoenixproject.ui.main.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phoenixproject.Models.ProductsModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.data.OnItemListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    List<ProductsModel> ProductsModelArrayList = new ArrayList<>();
    private OnItemListener onItemClicked;
    int lay ;

    public ProductsAdapter(OnItemListener onItemClicked,int lay) {
        this.onItemClicked = onItemClicked;
        this.lay = lay;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsViewHolder(LayoutInflater.from(parent.getContext()).inflate(lay, parent, false), onItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        holder.pName.setText(ProductsModelArrayList.get(position).getName());
        holder.pPriceIn.setText(ProductsModelArrayList.get(position).getPrice_in());
        Picasso.get().load(ProductsModelArrayList.get(position).getImg()).into(holder.pImage);


    }

    @Override
    public int getItemCount() {
        return ProductsModelArrayList.size();
    }


    public void setList(List<ProductsModel> moviesList) {
        ProductsModelArrayList = moviesList;
        notifyDataSetChanged();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView pName, pPriceIn;
        private ImageView pImage, edit;
        OnItemListener onItemClicked;

        public ProductsViewHolder(@NonNull View itemView, OnItemListener onItemClicked) {
            super(itemView);
            pName = itemView.findViewById(R.id.product_name);
            pPriceIn = itemView.findViewById(R.id.product_price);
            pImage = itemView.findViewById(R.id.product_image);
            edit = itemView.findViewById(R.id.edit_product);
            edit.setOnClickListener(this);
            this.onItemClicked = onItemClicked;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (v.equals(edit)) {
                onItemClicked.onEditClicked(getAdapterPosition());
            } else
                onItemClicked.onItemClicked(getAdapterPosition());

        }
    }
}
