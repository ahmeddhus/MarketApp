package com.example.marketapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.viewmodels.CategoriesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener {

    @BindView(R.id.categories_recycler)
    RecyclerView recyclerView;

    private CategoriesViewModel categoriesViewModel;
    private List<CategoryModel> categoryModels;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesViewModel.init();

        categoriesViewModel.getCategories().observe(this, categoryModels -> {

            if (categoryModels != null)
                setRV(categoryModels);
        });
    }

    private void setRV(List<CategoryModel> categoryModels) {
        categoryAdapter = new CategoryAdapter(MainActivity.this, categoryModels, this);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClikced(int position) {

    }
}
