package com.example.retrofitapp;

// ApiService.java

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") int postId);

    @GET("posts")
    Call<List<Post>> getAllPosts();
}
