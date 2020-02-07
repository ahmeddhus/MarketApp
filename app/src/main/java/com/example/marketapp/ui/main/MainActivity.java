package com.example.marketapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;

import com.example.marketapp.R;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.ui.category.CategoryActivity;
import com.example.marketapp.viewmodels.CategoriesViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.marketapp.utils.Constants.CATEGORY_ID;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener {

    @BindView(R.id.categories_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.imageSlider)
    SliderView sliderView;

    private List<CategoryModel> categoryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initImageSlider();
        getData();
    }

    private void initImageSlider() {
        sliderView.setSliderAdapter(new SliderAdapter(MainActivity.this));
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
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
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

    @Override
    public void onItemClicked(int position) {

        String categoryId = categoryModels.get(position).getId();

        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra(CATEGORY_ID, categoryId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
