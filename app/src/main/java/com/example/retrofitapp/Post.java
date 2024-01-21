package com.example.retrofitapp;

// Post.java

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(){
        this.userId = userId;
        this.id = id;
        this.body = body;
        this.title = title;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setBody(String body){
        this.body = body;
    }

    public int getId(){
        return id;
    }

    public int getUserId(){
        return userId;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    // Add getters and setters
}
