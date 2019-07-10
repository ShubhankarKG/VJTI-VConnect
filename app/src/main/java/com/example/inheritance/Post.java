package com.example.inheritance;

import com.google.firebase.database.IgnoreExtraProperties;

public class Post {
    private String title;
    private String description;
    private String image;
    private String date;
    private String id;



    public Post(String title, String description, String image, String date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public Post(){

    }
    public Post(String title, String description,String image){
        this.title = title;
        this.description = description;
        this.image = image;
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
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}