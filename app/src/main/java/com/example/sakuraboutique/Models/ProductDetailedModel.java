package com.example.sakuraboutique.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailedModel {

    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("CodeNo")
    @Expose
    private Integer codeNo;
    @SerializedName("Photos")
    @Expose
    private List<String> photos = null;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("ProductDescription")
    @Expose
    private String productDescription;
    @SerializedName("ProductID")
    @Expose
    private Integer productID;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("Sizes")
    @Expose
    private List<Size> sizes = null;

    public ProductDetailedModel(Integer categoryID, Integer codeNo, List<String> photos, Integer price, String productDescription, Integer productID, String productName, List<Size> sizes) {
        this.categoryID = categoryID;
        this.codeNo = codeNo;
        this.photos = photos;
        this.price = price;
        this.productDescription = productDescription;
        this.productID = productID;
        this.productName = productName;
        this.sizes = sizes;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(Integer codeNo) {
        this.codeNo = codeNo;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

}