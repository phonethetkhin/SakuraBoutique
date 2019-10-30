package com.example.sakuraboutique.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ProductDetailModel implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.productId);
        dest.writeInt(this.price);
        dest.writeInt(this.stockQuantity);
        dest.writeString(this.productname);
        dest.writeString(this.productDescription);
        dest.writeStringList(this.URLs);
        dest.writeStringList(this.Size);
        dest.writeStringList(this.Color);
    }

    protected ProductDetailModel(Parcel in) {
        this.productId = in.readInt();
        this.price = in.readInt();
        this.stockQuantity = in.readInt();
        this.productname = in.readString();
        this.productDescription = in.readString();
        this.URLs = in.createStringArrayList();
        this.Size = in.createStringArrayList();
        this.Color = in.createStringArrayList();
    }

    public static final Parcelable.Creator<ProductDetailModel> CREATOR = new Parcelable.Creator<ProductDetailModel>() {
        @Override
        public ProductDetailModel createFromParcel(Parcel source) {
            return new ProductDetailModel(source);
        }

        @Override
        public ProductDetailModel[] newArray(int size) {
            return new ProductDetailModel[size];
        }
    };
}
