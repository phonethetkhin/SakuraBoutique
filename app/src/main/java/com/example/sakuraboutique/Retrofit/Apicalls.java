package com.example.sakuraboutique.Retrofit;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Models.ProductModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apicalls {


    @GET("Categories.json")
    Call<List<CategoryModel>> getCategoryModellist();

    @GET("Products.json")
    Call<List<ProductDetailedModel>> getProductList(
            @Query("orderBy") String key,
            @Query("equalTo") int value,
            @Query("limitToFirst") int limit

    );



    @GET("Products.json")
    Call<JsonObject> getProductDetail(
            @Query("orderBy") String key,
            @Query("equalTo") int value


    );
}
