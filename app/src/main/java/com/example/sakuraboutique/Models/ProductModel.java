package com.example.sakuraboutique.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductModel implements Serializable {

    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("ProductID")
    @Expose
    private Integer productID;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("URL")
    @Expose
    private String uRL;

    public ProductModel(Integer categoryID, Integer price, Integer productID, String productName, String uRL) {
        this.categoryID = categoryID;
        this.price = price;
        this.productID = productID;
        this.productName = productName;
        this.uRL = uRL;
    }

    public ProductModel() {
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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