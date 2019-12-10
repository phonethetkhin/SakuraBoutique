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
    Call<List<ProductModel>> productmodellist;
    public LiveData<List<ProductModel>> getProductLiveData(int value)
    {
        final MutableLiveData<List<ProductModel>> productmutableLiveDatalist=new MutableLiveData<>();
        Apicalls apicalls= RetrofitObj.getRetrofit().create(Apicalls.class);
        if(value==1)
        {
            productmodellist=apicalls.getAllProducts("\"ProductID\"",10);
        }
            else if(value==2)
            {
                productmodellist=apicalls.getTops("\"ProductID\"",10);

            }

            else if(value==3)
        {
            productmodellist=apicalls.getBottoms("\"ProductID\"",10);
        }
        else if(value==4)
        {
            productmodellist=apicalls.getDresses("\"ProductID\"",10);
        }

        else if(value==5)
        {
            productmodellist=apicalls.getTraditionalDresses("\"ProductID\"",10);
        }
        else if(value==6)
        {
            productmodellist=apicalls.getShoesandBags("\"ProductID\"",10);
        }
        else if(value==7)
        {
            productmodellist=apicalls.getAccessories("\"ProductID\"",10);
        }
        else if(value==8)
        {
            productmodellist=apicalls.getMenWears("\"ProductID\"",10);
        }


            productmodellist.enqueue(new Callback<List<ProductModel>>() {
                @Override
                public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                    productmutableLiveDatalist.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<ProductModel>> call, Throwable t) {

                }
            });
        return productmutableLiveDatalist;
    }
}
