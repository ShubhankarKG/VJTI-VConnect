package com.example.inheritance;

public class Post {
    private String title;
    private String description;
    private String imageUrl;
    private String date;
    private String id;

    public Post() {
    }

    public Post(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Post(String title, String description, String imageUrl, String date) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    public String getTitle() {
        return title;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}