package com.example.sakuraboutique.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sakuraboutique.Models.Color;
import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Models.Size;
import com.example.sakuraboutique.Retrofit.Apicalls;
import com.example.sakuraboutique.Retrofit.RetrofitObj;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailedRepositories {
    int CategoryID;
    int CodeNo;
    int Price;
    String ProductDescription;
    int ProductID;
    String ProductName;
    String colorcode;
    String colorname;
    int quantity;
    Color colormodel;
    String SizeName;
    Size sizemodel;

    public LiveData<ProductDetailedModel> getproductdetailLiveData(final int PID)
    {
        final List<String> photolist=new ArrayList<>();
        //This is colorList AraryList. Only one
        List<Color> colorlist=new ArrayList<>();
        final List<Size> sizeList=new ArrayList<>();
          int Index=PID-1;
          final String FinalIndex=Index+"";



        final MutableLiveData<ProductDetailedModel> productDetailModelMutableLiveData=new MutableLiveData<>();
        Apicalls apicalls= RetrofitObj.getRetrofit().create(Apicalls.class);
        Call<JsonObject> jsonObjectCall=apicalls.getProductDetail("\"ProductID\"",PID);
        jsonObjectCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();
                JsonObject source=jsonObject.get(FinalIndex).getAsJsonObject();
                 CategoryID = source.get("CategoryID").getAsInt();

                for (int i = 0; i < source.get("Photos").getAsJsonArray().size();i++)
                {

                    String photo = source.get("Photos").getAsJsonArray().get(i).getAsString();
                    photolist.add(photo);
                }
                 Price=source.get("Price").getAsInt();
                CodeNo=source.get("CodeNo").getAsInt();
                 ProductDescription=source.get("ProductDescription").getAsString();
                 ProductID=source.get("ProductID").getAsInt();
                 ProductName=source.get("ProductName").getAsString();

                for(int i=0;i<source.get("Sizes").getAsJsonArray().size();i++) {

                    SizeName=source.get("Sizes").getAsJsonArray().get(i).getAsJsonObject().get("SizeName").getAsString();
                    //Empty Constructor
                    sizemodel=new Size();
                    //Add sizeName
                    sizemodel.setSizeName(SizeName);
                    //Here I instantiated ArrayList(mean new ArrayList()); So whenever i changes, Array will be new instance.
                    List<Color> colorlist = new ArrayList<>();

                    for(int k=0;k<source.get("Sizes").getAsJsonArray().get(i).getAsJsonObject().get("Color").getAsJsonArray().size();k++) {

        colorcode = source.get("Sizes").getAsJsonArray().get(i).getAsJsonObject().get("Color").getAsJsonArray().get(k).getAsJsonObject().get("ColorCode").getAsString();
        colorname = source.get("Sizes").getAsJsonArray().get(i).getAsJsonObject().get("Color").getAsJsonArray().get(k).getAsJsonObject().get("ColorName").getAsString();
        quantity = source.get("Sizes").getAsJsonArray().get(i).getAsJsonObject().get("Color").getAsJsonArray().get(k).getAsJsonObject().get("Quantity").getAsInt();

                        colormodel=new Color(colorcode,colorname,quantity);

                        colorlist.add(colormodel);



                    }
                    //We have two colors in colorList. So set it to color, child or size object
                    sizemodel.setColor(colorlist);
                    //Size list has one child size. Then i = 1;
                    sizeList.add(sizemodel);
                }
            ProductDetailedModel pdm=new ProductDetailedModel(CategoryID,CodeNo,photolist,Price,ProductDescription,ProductID,ProductName,sizeList);

productDetailModelMutableLiveData.setValue(pdm);
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        return productDetailModelMutableLiveData;
    }
}
