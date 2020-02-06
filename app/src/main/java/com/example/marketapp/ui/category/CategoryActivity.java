package com.example.marketapp.ui.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.utils.Constants;
import com.example.marketapp.viewmodels.ProductsViewModel;

import static com.example.marketapp.utils.Constants.CATEGORY_ID;

public class CategoryActivity extends AppCompatActivity {

    private ProductsViewModel productsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        String categoryId = intent.getStringExtra(CATEGORY_ID);

        Log.e("ID", categoryId + "");
        getData(categoryId);
    }

        private void getData(String id) {
        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.init();

        productsViewModel.getCategories(id).observe(this, productModels -> {
            if (productModels != null)
                Log.e("TEST", productModels.getProducts().get(0).getName());
        });
    }
}
