package com.example.sakuraboutique.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductCartModel implements Parcelable {
    private int ProductId,Price,Quantity,TotalPrice;
    private String ProductName,URLs,Size,Color;

    public ProductCartModel(int productId, int price, int quantity, int totalPrice, String productName, String URLs, String size, String color) {
        ProductId = productId;
        Price = price;
        Quantity = quantity;
        TotalPrice = totalPrice;
        ProductName = productName;
        this.URLs = URLs;
        Size = size;
        Color = color;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getURLs() {
        return URLs;
    }

    public void setURLs(String URLs) {
        this.URLs = URLs;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ProductId);
        dest.writeInt(this.Price);
        dest.writeInt(this.Quantity);
        dest.writeInt(this.TotalPrice);
        dest.writeString(this.ProductName);
        dest.writeString(this.URLs);
        dest.writeString(this.Size);
        dest.writeString(this.Color);
    }

    protected ProductCartModel(Parcel in) {
        this.ProductId = in.readInt();
        this.Price = in.readInt();
        this.Quantity = in.readInt();
        this.TotalPrice = in.readInt();
        this.ProductName = in.readString();
        this.URLs = in.readString();
        this.Size = in.readString();
        this.Color = in.readString();
    }

    public static final Creator<ProductCartModel> CREATOR = new Creator<ProductCartModel>() {
        @Override
        public ProductCartModel createFromParcel(Parcel source) {
            return new ProductCartModel(source);
        }

        @Override
        public ProductCartModel[] newArray(int size) {
            return new ProductCartModel[size];
        }
    };
}
