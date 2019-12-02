package com.example.sakuraboutique.Models;

public class Color {


    private String ColorCode;

    private int ColorID;

    public Color(String colorCode, int colorID) {
        ColorCode = colorCode;
        ColorID = colorID;
    }

    public String getColorCode() {
        return ColorCode;
    }

    public int getColorID() {
        return ColorID;
    }
}