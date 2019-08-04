package com.example.inheritance;

import com.google.firebase.database.IgnoreExtraProperties;

public class Post {
    private String title;
    private String description;
    private String imageUrl;
    private String pdfUrl;
    private String date;
    private String id;

    public Post(String title, String description, String imageUrl, String pdfUrl, String date) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.pdfUrl = pdfUrl;
        this.date = date;
    }

    public Post(String title, String description, String image , String date) {
        this.title = title;
        this.description = description;
        this.imageUrl = image;
        this.date = date;
    }

    public Post(){

    }
    public Post(String title, String description,String date){
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {return date;}

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {

        return imageUrl;

    }

    public void setImage(String image) {
        this.imageUrl = image;
    }
}