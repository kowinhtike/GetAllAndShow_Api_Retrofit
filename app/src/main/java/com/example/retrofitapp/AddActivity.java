package com.example.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    EditText titleInput ;
    EditText bodyInput;
    EditText userIdInput;
    Button postBtn;

    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        intialize();
        intializeLogic();
    }
    public void intialize(){
        titleInput = findViewById(R.id.titleInput);
        bodyInput = findViewById(R.id.bodyInput);
        userIdInput = findViewById(R.id.userIdInput);
        postBtn = findViewById(R.id.postBtn);
    }

    public void intializeLogic(){

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        postBtn.setOnClickListener(View -> {
            Post post = new Post();
            post.setTitle(titleInput.getText().toString());
            post.setBody(bodyInput.getText().toString());
            post.setUserId(Integer.valueOf(userIdInput.getText().toString()));

            Call<Post> call = apiService.createPost(post);
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        // Post created successfully
                        finish();
                    } else {
                        // Handle create failure
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    // Handle failure
                }
            });
        });
        }
}