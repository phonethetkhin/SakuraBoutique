package com.example.sakuraboutique.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Size {

    @SerializedName("Color")
    @Expose
    private List<Color> color = null;
    @SerializedName("SizeName")
    @Expose
    private String sizeName;

    public Size() {
    }

    public Size(List<Color> color, String sizeName) {
        this.color = color;
        this.sizeName = sizeName;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
}