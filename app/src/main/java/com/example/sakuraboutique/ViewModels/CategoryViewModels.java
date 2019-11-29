package com.example.sakuraboutique.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.Repositories.CategoryRepositories;

import java.util.List;

public class CategoryViewModels extends ViewModel {
    private CategoryRepositories categoryRepositories=new CategoryRepositories();
    private LiveData<List<CategoryModel>> categoryLivedata;
    public LiveData<List<CategoryModel>> getCategoryLivedata()
    {
        if(categoryLivedata==null)
        {
            categoryLivedata=categoryRepositories.getcategorylistfromApi();
        }
        return categoryLivedata;
    }

}
