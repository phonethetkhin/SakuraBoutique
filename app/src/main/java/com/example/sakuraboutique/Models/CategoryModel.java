package com.example.sakuraboutique.Models;

public class CategoryModel {


    private int CategoryID;

    private String CategoryName;

    private String PhotoURL;

    public CategoryModel(int categoryID, String categoryName, String photoURL) {
        CategoryID = categoryID;
        CategoryName = categoryName;
        PhotoURL = photoURL;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }
}