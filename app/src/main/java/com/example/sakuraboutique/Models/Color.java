package com.example.sakuraboutique.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color {

    @SerializedName("ColorCode")
    @Expose
    private String colorCode;
    @SerializedName("ColorName")
    @Expose
    private String colorName;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;

    public Color(String colorCode, String colorName, Integer quantity) {
        this.colorCode = colorCode;
        this.colorName = colorName;
        this.quantity = quantity;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}