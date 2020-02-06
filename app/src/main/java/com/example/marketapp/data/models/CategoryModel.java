package com.example.marketapp.data.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryModel {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("category_img")
    private String category_img;
    @SerializedName("products")
    private List<ProductModel> products;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory_img() {
        return category_img;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    @BindingAdapter({"bind:category_img"})
    public static void loadImage(ImageView view, String category_img) {
        Picasso.get()
                .load(category_img)
                .into(view);
    }
}
