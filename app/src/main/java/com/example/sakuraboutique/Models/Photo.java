package com.example.sakuraboutique.Models;

public class Photo {


    private int PhotoID;

    private String URL;

    public Photo(int photoID, String URL) {
        PhotoID = photoID;
        this.URL = URL;
    }

    public int getPhotoID() {
        return PhotoID;
    }

    public String getURL() {
        return URL;
    }
}