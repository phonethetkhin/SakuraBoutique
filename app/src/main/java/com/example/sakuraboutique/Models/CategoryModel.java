package com.example.sakuraboutique.Models;

public class CategoryModel {
    private String CategoryName,URL;

    public CategoryModel(String categoryName, String URL) {
        CategoryName = categoryName;
        this.URL = URL;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getURL() {
        return URL;
    }
}
