package com.example.sakuraboutique.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.Retrofit.Apicalls;
import com.example.sakuraboutique.Retrofit.RetrofitObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepositories {
    public LiveData<List<CategoryModel>> getcategorylistfromApi()
    {
        final MutableLiveData<List<CategoryModel>> mutableLiveData=new MutableLiveData<>();
        Apicalls apicalls= RetrofitObj.getRetrofit().create(Apicalls.class);
        Call<List<CategoryModel>> call=apicalls.getCategoryModellist();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    List<CategoryModel> categoryModels = response.body();
                    mutableLiveData.setValue(categoryModels);
                }
                else
                {
                    Log.d("Error","Error");
            }
        }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
