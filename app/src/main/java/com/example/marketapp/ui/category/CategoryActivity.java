package com.example.marketapp.ui.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryDeatailsModel;
import com.example.marketapp.data.models.ProductModel;
import com.example.marketapp.viewmodels.ProductsViewModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.marketapp.utils.Constants.CATEGORY_ID;

public class CategoryActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {

    @BindView(R.id.category_recycler)
    RecyclerView recyclerView;

    private CategoryDeatailsModel deatailsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        getData(getID());
    }

    private String getID() {
        Intent intent = getIntent();
        String categoryId = intent.getStringExtra(CATEGORY_ID);

        Log.e("ID", categoryId + "");
        return categoryId;
    }

    private void getData(String id) {
        ProductsViewModel productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.init();

        productsViewModel.getCategories(id).observe(this, productModels -> {
            if (productModels != null) {
                deatailsModels = productModels;
                setRV(productModels.products);
            }
        });
    }

    private void setRV(List<ProductModel> productModels) {
        ProductAdapter productAdapter = new ProductAdapter(CategoryActivity.this, productModels, this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClicked(int position) {

    }
}
