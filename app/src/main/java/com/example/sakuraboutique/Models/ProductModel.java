package com.example.sakuraboutique.Models;

public class ProductModel {
    private int ProductID, Price;
    private String Photo, ProductName;

    public ProductModel(int productID, int price, String photo, String productName) {
        ProductID = productID;
        Price = price;
        Photo = photo;
        ProductName = productName;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getPrice() {
        return Price;
    }

    public String getPhoto() {
        return Photo;
    }

    public String getProductName() {
        return ProductName;
    }
}
