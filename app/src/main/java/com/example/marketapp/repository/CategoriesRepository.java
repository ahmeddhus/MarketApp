package com.example.marketapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.data.retrofit.RetrofitClient;
import com.example.marketapp.data.retrofit.RetrofitService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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

        CompositeDisposable disposable = new CompositeDisposable();

        disposable.add(
                retrofitClient.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<CategoryModel>>() {
                            @Override
                            public void onSuccess(List<CategoryModel> categoryModels) {
                                newData.setValue(categoryModels);
                                Log.e("getCategories", "OK");
                            }

                            @Override
                            public void onError(Throwable e) {
                                newData.setValue(null);
                                if (e.getMessage() != null)
                                    Log.e("getCategoriesFailure", e.getMessage());
                            }
                        })
        );
        return newData;
    }
}
