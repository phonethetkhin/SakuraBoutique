package com.example.sakuraboutique.Models;

public class Size {


    private int SizeID;

    private String SizeName;

    public Size(int sizeID, String sizeName) {
        SizeID = sizeID;
        SizeName = sizeName;
    }

    public int getSizeID() {
        return SizeID;
    }

    public String getSizeName() {
        return SizeName;
    }
}