package com.example.sakuraboutique.Models;

import java.util.List;

public class ProductDetailModel {
    private int productId,price,stockQuantity;
    private String productname,productDescription;
    private List<String> URLs,Size,Color;

    public ProductDetailModel(int productId, int price, int stockQuantity, String productname, String productDescription, List<String> URLs, List<String> size, List<String> color) {
        this.productId = productId;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productname = productname;
        this.productDescription = productDescription;
        this.URLs = URLs;
        Size = size;
        Color = color;
    }

    public int getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public List<String> getURLs() {
        return URLs;
    }

    public List<String> getSize() {
        return Size;
    }

    public List<String> getColor() {
        return Color;
    }
}
