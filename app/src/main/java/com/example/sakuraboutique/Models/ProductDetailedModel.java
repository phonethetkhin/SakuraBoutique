package com.example.sakuraboutique.Models;

import java.util.List;

public class ProductDetailedModel {


    private int CategoryID;

    private List<Color> Color;

    private List<Photo> Photo;

    private int Price;

    private String ProductDescription;

    private int ProductID;

    private String ProductName;

    private int Quantity;

    private List<Size> Size;

    public ProductDetailedModel(int categoryID, List<com.example.sakuraboutique.Models.Color> color, List<com.example.sakuraboutique.Models.Photo> photo, int price, String productDescription, int productID, String productName, int quantity, List<com.example.sakuraboutique.Models.Size> size) {
        CategoryID = categoryID;
        Color = color;
        Photo = photo;
        Price = price;
        ProductDescription = productDescription;
        ProductID = productID;
        ProductName = productName;
        Quantity = quantity;
        Size = size;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public List<com.example.sakuraboutique.Models.Color> getColor() {
        return Color;
    }

    public List<com.example.sakuraboutique.Models.Photo> getPhoto() {
        return Photo;
    }

    public int getPrice() {
        return Price;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public List<com.example.sakuraboutique.Models.Size> getSize() {
        return Size;
    }
}