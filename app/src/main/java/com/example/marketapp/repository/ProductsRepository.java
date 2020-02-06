package com.example.marketapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.marketapp.data.models.CategoryDeatailsModel;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.data.models.ProductModel;
import com.example.marketapp.data.retrofit.RetrofitClient;
import com.example.marketapp.data.retrofit.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository {

    private RetrofitClient retrofitClient;

    private static ProductsRepository productsRepository;

    public static ProductsRepository getInstance() {
        if (productsRepository == null) {
            productsRepository = new ProductsRepository();
        }
        return productsRepository;
    }

    private ProductsRepository() {
        retrofitClient = RetrofitService.getRetro().create(RetrofitClient.class);
    }


    public MutableLiveData<CategoryDeatailsModel> getProducts(String id) {

        final MutableLiveData<CategoryDeatailsModel> newData = new MutableLiveData<>();

        retrofitClient.getCategory(id).enqueue(new Callback<CategoryDeatailsModel>() {
            @Override
            public void onResponse(@NotNull Call<CategoryDeatailsModel> call, @NotNull Response<CategoryDeatailsModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getProducts: ", response.message());
            }

            @Override
            public void onFailure(@NotNull Call<CategoryDeatailsModel> call, @NotNull Throwable t) {
                newData.setValue(null);
                if (t.getMessage() != null)
                    Log.e("getProductsFailure: ", t.getMessage());
            }
        });

        return newData;
    }
}
