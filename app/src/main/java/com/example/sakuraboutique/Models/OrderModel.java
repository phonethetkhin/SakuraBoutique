package com.example.sakuraboutique.Models;

import java.io.Serializable;

public class OrderModel implements Serializable {

    private String FullName, Address, PhoneNumber, Size, Color, Quantity, TotalPrice, TotalQuantity, TotalItem,ProductID,OrderDate;

    public OrderModel(String fullName, String address, String phoneNumber, String size, String color, String quantity, String totalPrice, String totalQuantity, String totalItem, String productID, String orderDate) {
        FullName = fullName;
        Address = address;
        PhoneNumber = phoneNumber;
        Size = size;
        Color = color;
        Quantity = quantity;
        TotalPrice = totalPrice;
        TotalQuantity = totalQuantity;
        TotalItem = totalItem;
        ProductID = productID;
        OrderDate = orderDate;
    }

    public OrderModel() {
    }

    public String getFullName() {
        return FullName;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getSize() {
        return Size;
    }

    public String getColor() {
        return Color;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public String getTotalItem() {
        return TotalItem;
    }

    public String getProductID() {
        return ProductID;
    }

    public String getOrderDate() {
        return OrderDate;
    }
}
