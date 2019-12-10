package com.example.sakuraboutique.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.Repositories.ProductRepositories;

import java.util.List;

public class ProductViewModel extends ViewModel {
    public LiveData<List<ProductModel>> productlivedatalist;
    private ProductRepositories repositories=new ProductRepositories();

    public LiveData<List<ProductModel>> getProductlivedatalist(int value)
    {
        if(productlivedatalist==null)
        {
            productlivedatalist=repositories.getProductLiveData(value);
        }
        return productlivedatalist;
    }
}
