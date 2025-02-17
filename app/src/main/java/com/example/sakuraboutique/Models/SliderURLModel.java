package com.example.sakuraboutique.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderURLModel {

    @SerializedName("ProductID")
    @Expose
    private Integer productID;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("URL")
    @Expose
    private String uRL;

    public SliderURLModel(Integer productID, String productName, String uRL) {
        this.productID = productID;
        this.productName = productName;
        this.uRL = uRL;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

}