package com.example.inheritance;

import com.google.firebase.database.IgnoreExtraProperties;

public class Post {

    private String postDate, postTitle, postDescription, postContent;

    public Post(String postTitle, String postDescription) {
        this.postTitle = postTitle;
        this.postDescription = postDescription; // postDescription, if given then okay, otherwise excerpt processed here?
        this.postContent = postContent;
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

    public String getPostContent() {
        return postContent;
    }
}
