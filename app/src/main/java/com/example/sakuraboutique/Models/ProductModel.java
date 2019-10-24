package com.example.sakuraboutique.Models;

public class ProductModel {
    private int ProductID,Price,Photo;
    private String ProductName;

    public ProductModel(int productID, int price, int photo, String productName) {
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

    public int getPhoto() {
        return Photo;
    }

    public String getProductName() {
        return ProductName;
    }
}
