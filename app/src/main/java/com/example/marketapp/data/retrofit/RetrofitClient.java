package com.example.marketapp.data.retrofit;

import com.example.marketapp.data.models.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitClient {

    @GET("task/categories")
    Call<List<CategoryModel>> getCategories();

    @GET("task/categories/{id}")
    Call<CategoryModel> getCategory(@Path("id") String id);
}
