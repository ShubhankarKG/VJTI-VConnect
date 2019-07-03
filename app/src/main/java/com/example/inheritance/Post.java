package com.example.inheritance;

public class Post {

    private String postDate, postTitle, postDescription;

    public Post(String postTitle, String postDescription) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public String getPostDate() {
        return postDate;
    }
}
