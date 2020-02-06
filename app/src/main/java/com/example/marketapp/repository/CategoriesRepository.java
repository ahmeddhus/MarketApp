package com.example.marketapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.data.retrofit.RetrofitClient;
import com.example.marketapp.data.retrofit.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesRepository {

    private RetrofitClient retrofitClient;
    private static CategoriesRepository categoriesRepository;

    public static CategoriesRepository getInstance() {
        if (categoriesRepository == null) {
            categoriesRepository = new CategoriesRepository();
        }
        return categoriesRepository;
    }

    private CategoriesRepository() {
        retrofitClient = RetrofitService.getRetro().create(RetrofitClient.class);
    }

    public MutableLiveData<List<CategoryModel>> getCategories() {

        final MutableLiveData<List<CategoryModel>> newData = new MutableLiveData<>();

        retrofitClient.getCategories().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CategoryModel>> call, @NotNull Response<List<CategoryModel>> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getCategories: ", response.message());
            }

            @Override
            public void onFailure(@NotNull Call<List<CategoryModel>> call, @NotNull Throwable t) {
                newData.setValue(null);
                if (t.getMessage() != null)
                    Log.e("getCategoriesFailure: ", t.getMessage());
            }
        });

        return newData;
    }
}
