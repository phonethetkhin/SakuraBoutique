package com.example.sakuraboutique.Models;

public class ProductModel {
    private int ProductID,Price;
    private String ProductName,URLs;

    public ProductModel(int productID, int price, String URL,String productName)
            {
        ProductID = productID;
        Price = price;
        this.URLs= URL;

        ProductName = productName;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getPrice() {
        return Price;
    }



    public String getURL() {
        return URLs;
    }
    public String getProductName() {
        return ProductName;
    }
}
