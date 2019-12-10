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

//category
    @GET("Categories.json")
    Call<List<CategoryModel>> getCategoryModellist();

    @GET("SliderURL.json")
    Call<JsonArray> getURLs();

    //productlist
    @GET("AllProducts.json")
    Call<List<ProductModel>> getAllProducts(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );
    @GET("Tops.json")
    Call<List<ProductModel>> getTops(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );
    @GET("Bottoms.json")
    Call<List<ProductModel>> getBottoms(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );
    @GET("Dresses.json")
    Call<List<ProductModel>> getDresses(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );
    @GET("TraditionalDresses.json")
    Call<List<ProductModel>> getTraditionalDresses(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );
    @GET("ShoesandBags.json")
    Call<List<ProductModel>> getShoesandBags(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );
    @GET("Accessories.json")
    Call<List<ProductModel>> getAccessories(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );
    @GET("MenWears.json")
    Call<List<ProductModel>> getMenWears(
            @Query("orderBy") String key,
            @Query("limitToFirst") int limit

    );





//productdetail
    @GET("Products.json")
    Call<JsonObject> getProductDetail(
            @Query("orderBy") String key,
            @Query("equalTo") int value


    );
}
