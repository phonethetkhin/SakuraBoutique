package com.example.sakuraboutique.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Retrofit.Apicalls;
import com.example.sakuraboutique.Retrofit.RetrofitObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailedRepositories {
   /* public LiveData<List<ProductDetailedModel>> getproductdetailLiveData()
    {
        final MutableLiveData<List<ProductDetailedModel>> productDetailModelMutableLiveData=new MutableLiveData<>();
        Apicalls apicalls= RetrofitObj.getRetrofit().create(Apicalls.class);
        Call<List<ProductDetailedModel>> call=apicalls.getProductDetail();
        call.enqueue(new Callback<List<ProductDetailedModel>>() {
            @Override
            public void onResponse(Call<List<ProductDetailedModel>> call, Response<List<ProductDetailedModel>> response) {
                if(response.isSuccessful()) {
                    productDetailModelMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<ProductDetailedModel>> call, Throwable t) {

            }
        });
        return productDetailModelMutableLiveData;
    }*/
}
