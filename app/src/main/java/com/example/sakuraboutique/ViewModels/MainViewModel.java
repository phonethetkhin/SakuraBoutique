package com.example.sakuraboutique.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.R;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
private static List<CategoryModel> CategoryModelList=new ArrayList<>();

    public static List<CategoryModel> AddData()
    {
        CategoryModelList.add(new CategoryModel("New Arrivals", R.drawable.dress1));
        CategoryModelList.add(new CategoryModel("Tops",R.drawable.dress2));
        CategoryModelList.add(new CategoryModel("Bottoms",R.drawable.dress3));
        CategoryModelList.add(new CategoryModel("Dresses",R.drawable.dress4));
        CategoryModelList.add(new CategoryModel("Traditional Dresses",R.drawable.dress5));
        CategoryModelList.add(new CategoryModel("Shoes & Bags",R.drawable.dress1));
        CategoryModelList.add(new CategoryModel("Accessories",R.drawable.dress2));
        CategoryModelList.add(new CategoryModel("Men's Fashion",R.drawable.dress3));
    return CategoryModelList;
    }

}
