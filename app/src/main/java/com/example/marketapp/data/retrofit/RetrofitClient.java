package com.example.marketapp.data.retrofit;

import com.example.marketapp.data.models.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitClient {

    @GET("task/categories")
    Call<List<CategoryModel>> getCategories();
}
