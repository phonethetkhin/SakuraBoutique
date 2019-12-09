package com.example.sakuraboutique.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.Retrofit.Apicalls;
import com.example.sakuraboutique.Retrofit.RetrofitObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositories {
    public LiveData<List<ProductDetailedModel>> getProductLiveData(int value)
    {
        final MutableLiveData<List<ProductDetailedModel>> productmutableLiveDatalist=new MutableLiveData<>();
        Apicalls apicalls= RetrofitObj.getRetrofit().create(Apicalls.class);
        Call<List<ProductDetailedModel>> productmodellist=apicalls.getProductList("\"CategoryID\"",value,10);
        productmodellist.enqueue(new Callback<List<ProductDetailedModel>>() {
            @Override
            public void onResponse(Call<List<ProductDetailedModel>> call, Response<List<ProductDetailedModel>> response) {
                if(response.isSuccessful()) {
                    List<ProductDetailedModel> productModellist = response.body();
                    productmutableLiveDatalist.setValue(productModellist);
                }
                else
                {
                    Log.d("Error","error");
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetailedModel>> call, Throwable t) {

            }
        });
        return productmutableLiveDatalist;
    }
}
