package com.example.sakuraboutique.Models;

public class CategoryModel {
    private String CategoryName;
    private int PhotoURL;

    public CategoryModel(String categoryName, int photoURL) {
        CategoryName = categoryName;
        PhotoURL = photoURL;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public int getPhotoURL() {
        return PhotoURL;
    }
}
