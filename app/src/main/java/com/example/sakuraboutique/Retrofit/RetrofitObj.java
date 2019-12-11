package com.example.sakuraboutique.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObj {
    public static final String BASE_URL="https://sakura-boutique.firebaseio.com/";
    private static  Retrofit retrofit=null;
    public static Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHTTPClient())
                    .build();
        }
        return retrofit;
    }
    public static OkHttpClient getOkHTTPClient()
    {
        OkHttpClient client=new OkHttpClient();
        OkHttpClient.Builder clientBuilder=client.newBuilder();

        clientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        clientBuilder.readTimeout(60, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(60, TimeUnit.SECONDS);
        return clientBuilder.build();
    }

}
