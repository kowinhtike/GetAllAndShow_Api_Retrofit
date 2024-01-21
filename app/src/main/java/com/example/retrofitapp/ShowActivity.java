package com.example.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends AppCompatActivity {

    TextView title;
    TextView body;

    Button editBtn;
    Button deleteBtn;
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        intialize();
        intializeLogic();
    }

    public  void intialize(){
        title = findViewById(R.id.titleText);
        body = findViewById(R.id.bodyText);
        editBtn = findViewById(R.id.postButton);
        deleteBtn = findViewById(R.id.deleteBtn);
    }

    public void intializeLogic(){
        editBtn.setOnClickListener(View -> {
            Intent i = new Intent(this,EditActivity.class);
            i.putExtra("id",getIntent().getIntExtra("id",0));
            startActivity(i);
        });

        deleteBtn.setOnClickListener(View -> {
            Call<Void> call = apiService.deletePostById(getIntent().getIntExtra("id",0));

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Post deleted successfully
                        finish();
                    } else {
                        // Handle delete failure
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle failure
                }
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Post> call = apiService.getPostById(getIntent().getIntExtra("id",0));
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post post = response.body();
                    title.setText(post.getTitle());
                    body.setText(post.getBody());
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
    }
}