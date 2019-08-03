package com.example.inheritance;

public class Upload {
    public String title;
    public String url;
    public String description;
    public String id;

    public Upload( String url, String title, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public Upload() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
