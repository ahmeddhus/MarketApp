package com.example.marketapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.ui.category.CategoryActivity;
import com.example.marketapp.viewmodels.CategoriesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.marketapp.utils.Constants.CATEGORY_ID;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener {

    @BindView(R.id.categories_recycler)
    RecyclerView recyclerView;

    private List<CategoryModel> categoryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        CategoriesViewModel categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesViewModel.init();

        categoriesViewModel.getCategories().observe(this, categoryModels -> {

            if (categoryModels != null) {
                this.categoryModels = categoryModels;
                setRV(categoryModels);
            }
        });
    }

    private void setRV(List<CategoryModel> categoryModels) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, categoryModels, this);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClicked(int position) {

        String categoryId = categoryModels.get(position).getId();

        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra(CATEGORY_ID, categoryId);
        startActivity(intent);
    }
}
