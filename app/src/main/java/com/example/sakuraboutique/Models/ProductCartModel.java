package com.example.sakuraboutique.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductCartModel implements Parcelable {
    private int ProductId,Price,Quantity;
    private String ProductName,URLs,Size,Color;

    public ProductCartModel(int productId, int price, int quantity, String productName, String URLs, String size, String color) {
        ProductId = productId;
        Price = price;
        Quantity = quantity;
        ProductName = productName;
        this.URLs = URLs;
        Size = size;
        Color = color;
    }

    public int getProductId() {
        return ProductId;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getURLs() {
        return URLs;
    }

    public String getSize() {
        return Size;
    }

    public String getColor() {
        return Color;
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
        dest.writeString(this.ProductName);
        dest.writeString(this.URLs);
        dest.writeString(this.Size);
        dest.writeString(this.Color);
    }

    protected ProductCartModel(Parcel in) {
        this.ProductId = in.readInt();
        this.Price = in.readInt();
        this.Quantity = in.readInt();
        this.ProductName = in.readString();
        this.URLs = in.readString();
        this.Size = in.readString();
        this.Color = in.readString();
    }

    public static final Parcelable.Creator<ProductCartModel> CREATOR = new Parcelable.Creator<ProductCartModel>() {
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
