package com.example.marketapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marketapp.data.models.CategoryModel;
import com.example.marketapp.repository.CategoriesRepository;

import java.util.List;

public class CategoriesViewModel extends ViewModel {

    private MutableLiveData<List<CategoryModel>> mutableLiveData;

    private CategoriesRepository categoriesRepository;

    public void init(){
        if (mutableLiveData != null)
            return;

        categoriesRepository = CategoriesRepository.getInstance();
    }

    public LiveData<List<CategoryModel>> getCategories(){
        mutableLiveData = categoriesRepository.getCategories();
        return mutableLiveData;
    }
}
