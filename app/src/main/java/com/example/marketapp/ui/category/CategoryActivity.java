package com.example.marketapp.ui.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryDeatailsModel;
import com.example.marketapp.data.models.ProductModel;
import com.example.marketapp.ui.main.MainActivity;
import com.example.marketapp.ui.product.ProductsActivity;
import com.example.marketapp.viewmodels.ProductsViewModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.marketapp.utils.Constants.CATEGORY_ID;
import static com.example.marketapp.utils.Constants.PRODUCT_NAME;

public class CategoryActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.category_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.toolbarImage)
    ImageView toolbarImage;

    private CategoryDeatailsModel deatailsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        init();
        getData(getID());
    }

    private void init(){

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

                toolbar.setTitle(productModels.getName());
                Glide.with(CategoryActivity.this)
                        .load(productModels.getCategory_img())
                        .into(toolbarImage);
            }
        });
    }

    private void setRV(List<ProductModel> productModels) {
        ProductAdapter productAdapter = new ProductAdapter(CategoryActivity.this, productModels, this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 2));
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
        intent.putExtra(PRODUCT_NAME, deatailsModels.products.get(position).getName());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
