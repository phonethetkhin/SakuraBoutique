package com.example.sakuraboutique.Retrofit;

import com.example.sakuraboutique.Models.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apicalls {


    @GET("Categories.json")
    Call<List<CategoryModel>> getCategoryModellist();
}
