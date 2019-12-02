package com.example.sakuraboutique.Models;

public class ProductModel {
    private int ProductID, Price;
    private String PhotoURL, ProductName;

    public ProductModel(int productID, int price, String photoURL, String productName) {
        ProductID = productID;
        Price = price;
        PhotoURL = photoURL;
        ProductName = productName;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getPrice() {
        return Price;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public String getProductName() {
        return ProductName;
    }
}
