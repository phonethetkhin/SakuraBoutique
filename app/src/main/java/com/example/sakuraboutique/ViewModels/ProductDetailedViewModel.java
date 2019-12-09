package com.example.sakuraboutique.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Repositories.ProductDetailedRepositories;

public class ProductDetailedViewModel extends ViewModel {
     public LiveData<ProductDetailedModel> productlivedatalist;
    private ProductDetailedRepositories repositories=new ProductDetailedRepositories();

    public LiveData<ProductDetailedModel> getProductlivedatalist(int ProductID)
    {
        if(productlivedatalist==null)
        {
            productlivedatalist=repositories.getproductdetailLiveData(ProductID);
        }
        return productlivedatalist;
    }
}
