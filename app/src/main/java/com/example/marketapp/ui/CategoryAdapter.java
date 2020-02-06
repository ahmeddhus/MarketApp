package com.example.marketapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.databinding.CategoryItemBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemViewHolder> {

    Context context;
    List<CategoryModel> items;
    CategoryAdapter.OnItemClickListener mOnItemClickListener;

    public CategoryAdapter(Context context, List<CategoryModel> items, OnItemClickListener mOnItemClickListener) {
        this.context = context;
        this.items = items;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CategoryItemBinding categoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.category_item, parent, false);

        return new CategoryAdapter.ItemViewHolder(categoryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClikced(int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CategoryItemBinding categoryItemBinding;


        public ItemViewHolder(@NonNull CategoryItemBinding itemView) {
            super(itemView.getRoot());
            categoryItemBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        void bind(int position) {
            CategoryModel results = items.get(position);
            categoryItemBinding.setItem(results);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClikced(getAdapterPosition());
        }
    }
}
