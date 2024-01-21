package com.example.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    EditText titleInput ;
    EditText bodyInput;
    EditText userIdInput;
    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        intialize();
        intializeLogic();
    }

    public void intialize(){
        titleInput = findViewById(R.id.titleInput);
        bodyInput = findViewById(R.id.bodyInput);
        userIdInput = findViewById(R.id.userIdInput);
        updateBtn = findViewById(R.id.editBtn);
    }

    public void intializeLogic(){

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Post> call = apiService.getPostById(getIntent().getIntExtra("id",0));
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post post = response.body();
                    titleInput.setText(post.getTitle());
                    bodyInput.setText(post.getBody());
                    userIdInput.setText(String.valueOf(post.getUserId()));
                    // Handle the data here
                } else {
                    // Handle errors
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                // Handle failure
            }
        });

        updateBtn.setOnClickListener(View -> {
            int postId = getIntent().getIntExtra("id",0);
            Post post = new Post();
            post.setTitle(titleInput.getText().toString());
            post.setBody(bodyInput.getText().toString());
            post.setUserId(Integer.valueOf(userIdInput.getText().toString()));

            //call update api
            Call<Post> updatePostCall = apiService.updatePost(postId,post);
            updatePostCall.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        // Handle successful update
                        finish();
                    } else {
                        // Handle update errors
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    // Handle update failure
                }
            });
        });
    }
}