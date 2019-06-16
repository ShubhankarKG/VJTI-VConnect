package com.example.inheritance.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Feed {

    String date;
    String title;
    String description;

    public Feed() { }

    public Feed(String date, String title, String description){
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public Feed(String title, String description){
        String date = new SimpleDateFormat("dd-mm-yyyy" , Locale.getDefault()).format(new Date());
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title){
        this.title = title ;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }



}
