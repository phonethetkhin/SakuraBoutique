package com.example.sakuraboutique.Retrofit;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Models.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apicalls {


    @GET("Categories.json")
    Call<List<CategoryModel>> getCategoryModellist();

    @GET("ProductView.json")
    Call<List<ProductModel>> getProductList();

    @GET("Products.json")
    Call<List<ProductDetailedModel>> getProductDetail();
}
