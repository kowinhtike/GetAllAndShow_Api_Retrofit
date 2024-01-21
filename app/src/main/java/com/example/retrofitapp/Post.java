package com.example.retrofitapp;

// Post.java

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(Integer userId,Integer id,String title,String body){
        this.userId = userId;
        this.id = id;
        this.body = body;
        this.title = title;
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
