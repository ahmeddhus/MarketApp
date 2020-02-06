package com.example.marketapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.marketapp.data.models.CategoryDeatailsModel;

import com.example.marketapp.data.retrofit.RetrofitClient;
import com.example.marketapp.data.retrofit.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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
        CompositeDisposable disposable = new CompositeDisposable();

        disposable.add(
                retrofitClient.getCategory(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<CategoryDeatailsModel>() {
                            @Override
                            public void onSuccess(CategoryDeatailsModel categoryDeatailsModel) {
                                newData.setValue(categoryDeatailsModel);
                                Log.e("getProducts", "OK");
                            }

                            @Override
                            public void onError(Throwable e) {
                                newData.setValue(null);
                                if (e.getMessage() != null)
                                    Log.e("getProductsFailure", e.getMessage());
                            }
                        })
        );
        return newData;
    }
}