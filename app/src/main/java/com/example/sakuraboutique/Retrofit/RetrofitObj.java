package com.example.sakuraboutique.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObj {
    public static final String BASE_URL="https://sakura-boutique.firebaseio.com/";
    private static  Retrofit retrofit=null;
    public static Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
