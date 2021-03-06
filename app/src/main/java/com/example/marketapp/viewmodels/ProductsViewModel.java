package com.example.marketapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marketapp.data.models.CategoryDeatailsModel;
import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.repository.ProductsRepository;

public class ProductsViewModel extends ViewModel {

    private MutableLiveData<CategoryDeatailsModel> mutableLiveData;

    private ProductsRepository productsRepository;

    public void init(){
        if (mutableLiveData != null)
            return;

        productsRepository = ProductsRepository.getInstance();
    }

    public LiveData<CategoryDeatailsModel> getCategories(String id){
        mutableLiveData = productsRepository.getProducts(id);
        return mutableLiveData;
    }
}
