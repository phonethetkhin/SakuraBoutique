package com.example.sakuraboutique.Models;

import java.io.Serializable;
import java.util.List;

public class OrderModel implements Serializable {
    private String FullName,DeliveryAddress,PhoneNumber,OrderDate,TotalItem,TotalQuantity,TotalPrice;
        List<Items> itemsList;

    public OrderModel(String fullName, String deliveryAddress, String phoneNumber, String orderDate, String totalItem, String totalQuantity, String totalPrice, List<Items> itemsList) {
        FullName = fullName;
        DeliveryAddress = deliveryAddress;
        PhoneNumber = phoneNumber;
        OrderDate = orderDate;
        TotalItem = totalItem;
        TotalQuantity = totalQuantity;
        TotalPrice = totalPrice;
        this.itemsList = itemsList;
    }

    public OrderModel() {
    }

    public String getFullName() {
        return FullName;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public String getTotalItem() {
        return TotalItem;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }


}
