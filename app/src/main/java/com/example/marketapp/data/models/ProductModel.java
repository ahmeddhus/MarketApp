package com.example.marketapp.data.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class ProductModel {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("weight")
    private String weight;
    @SerializedName("price")
    private String price;
    @SerializedName("product_img")
    private String product_img;

    public ProductModel(String id, String name, String weight, String price, String product_img) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.product_img = product_img;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getPrice() {
        return price;
    }

    public String getProduct_img() {
        return product_img;
    }

    @BindingAdapter({"bind:product_img"})
    public static void loadImage(ImageView view, String product_img) {
        Picasso.get()
                .load(product_img)
                .into(view);
    }
}
