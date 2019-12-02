package com.example.sakuraboutique.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.Retrofit.Apicalls;
import com.example.sakuraboutique.Retrofit.RetrofitObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositories {
   /* public LiveData<List<ProductModel>> getProductLiveData()
    {
        final MutableLiveData<List<ProductModel>> productmutableLiveDatalist=new MutableLiveData<>();
        Apicalls apicalls= RetrofitObj.getRetrofit().create(Apicalls.class);
        Call<List<ProductModel>> productmodellist=apicalls.getProductList();
        productmodellist.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                List<ProductModel> productModellist=response.body();
                productmutableLiveDatalist.setValue(productModellist);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
        return productmutableLiveDatalist;
    }*/
}
