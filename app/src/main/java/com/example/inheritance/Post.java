package com.example.inheritance;

public class Post {
    private String title;
    private String description;
    private String imageUrl;
    private String date;
    private String id;

    //Default constructor for using Firebase Database
    public Post(){

    }

    //Constructor for creating a node when image is present.
    public Post(String title, String description, String imageUrl , String date) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    //Constructor for creating a node when image is not present.
    public Post(String title, String description,String date){
        this.title = title;
        this.description = description;
        this.date = date;
    }

    //Getter methods
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    String getDate() {return date;}
    String getDescription() {
        return description;
    }
    String getImageUrl() {
        return imageUrl;
    }

    //Setter Methods
    public void setId(String id) { this.id = id;}
    public void setTitle(String title) { this.title = title; }
}