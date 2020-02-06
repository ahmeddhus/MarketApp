package com.example.marketapp.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.data.models.ProductModel;
import com.example.marketapp.databinding.ProductItemBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemViewHolder> {

    private Context context;
    private List<ProductModel> items;
    private ProductAdapter.OnItemClickListener mOnItemClickListener;

    public ProductAdapter(Context context, List<ProductModel> items, OnItemClickListener mOnItemClickListener) {
        this.context = context;
        this.items = items;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ProductAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding productItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false);

        return new ProductAdapter.ItemViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ProductItemBinding productItemBinding;

        public ItemViewHolder(@NonNull ProductItemBinding itemView) {
            super(itemView.getRoot());
            productItemBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        void bind(int position) {
            ProductModel results = items.get(position);
            productItemBinding.setItem(results);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClicked(getAdapterPosition());
        }
    }
}
