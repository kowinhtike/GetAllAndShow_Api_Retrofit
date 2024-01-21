package com.example.retrofitapp;

// ApiService.java

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") int postId);

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @PUT("posts/{id}")
    Call<Post> updatePost(@Path("id") int postId, @Body Post updatedPost);

    @DELETE("posts/{id}")
    Call<Void> deletePostById(@Path("id") int postId);
}
