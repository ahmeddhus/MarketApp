package com.example.marketapp.data.retrofit;

import com.example.marketapp.data.models.CategoryDeatailsModel;
import com.example.marketapp.data.models.CategoryModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitClient {

    @GET("task/categories")
    Single<List<CategoryModel>> getCategories();

    @GET("task/categories/{id}")
    Single<CategoryDeatailsModel> getCategory(@Path("id") String id);
}
