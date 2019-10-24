package com.example.sakuraboutique.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.R;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private static List<ProductModel> ProductModelList=new ArrayList<>();

    public static List<ProductModel> AddData()
    {
        ProductModelList.add(new ProductModel(001,13000, R.drawable.dress1,"Yellow Dress"));
        ProductModelList.add(new ProductModel(002,14000,R.drawable.dress2,"Grey Dress"));
        ProductModelList.add(new ProductModel(003,15000,R.drawable.dress3,"Black Dress"));
        ProductModelList.add(new ProductModel(004,16000,R.drawable.dress4,"White Dress"));
        ProductModelList.add(new ProductModel(005,17000,R.drawable.dress5,"Pink Dress"));

        ProductModelList.add(new ProductModel(001,13000,R.drawable.dress1,"Yellow Dress"));
        ProductModelList.add(new ProductModel(002,14000,R.drawable.dress2,"Grey Dress"));
        ProductModelList.add(new ProductModel(003,15000,R.drawable.dress3,"Black Dress"));
        ProductModelList.add(new ProductModel(004,16000,R.drawable.dress4,"White Dress"));
        ProductModelList.add(new ProductModel(005,17000,R.drawable.dress5,"Pink Dress"));

        ProductModelList.add(new ProductModel(001,13000,R.drawable.dress1,"Yellow Dress"));
        ProductModelList.add(new ProductModel(002,14000,R.drawable.dress2,"Grey Dress"));
        ProductModelList.add(new ProductModel(003,15000,R.drawable.dress3,"Black Dress"));
        ProductModelList.add(new ProductModel(004,16000,R.drawable.dress4,"White Dress"));
        ProductModelList.add(new ProductModel(005,17000,R.drawable.dress5,"Pink Dress"));

        ProductModelList.add(new ProductModel(001,13000,R.drawable.dress1,"Yellow Dress"));
        ProductModelList.add(new ProductModel(002,14000,R.drawable.dress2,"Grey Dress"));
        ProductModelList.add(new ProductModel(003,15000,R.drawable.dress3,"Black Dress"));
        ProductModelList.add(new ProductModel(004,16000,R.drawable.dress4,"White Dress"));
        ProductModelList.add(new ProductModel(005,17000,R.drawable.dress5,"Pink Dress"));

        ProductModelList.add(new ProductModel(001,13000,R.drawable.dress1,"Yellow Dress"));
        ProductModelList.add(new ProductModel(002,14000,R.drawable.dress2,"Grey Dress"));
        ProductModelList.add(new ProductModel(003,15000,R.drawable.dress3,"Black Dress"));
        ProductModelList.add(new ProductModel(004,16000,R.drawable.dress4,"White Dress"));
        ProductModelList.add(new ProductModel(005,17000,R.drawable.dress5,"Pink Dress"));

        ProductModelList.add(new ProductModel(001,13000,R.drawable.dress1,"Yellow Dress"));
        ProductModelList.add(new ProductModel(002,14000,R.drawable.dress2,"Grey Dress"));
        ProductModelList.add(new ProductModel(003,15000,R.drawable.dress3,"Black Dress"));
        ProductModelList.add(new ProductModel(004,16000,R.drawable.dress4,"White Dress"));
        ProductModelList.add(new ProductModel(005,17000,R.drawable.dress5,"Pink Dress"));
        return ProductModelList;
    }
}
