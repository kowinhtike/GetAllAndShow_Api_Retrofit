package com.example.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends AppCompatActivity {

    TextView title;
    TextView body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        title = findViewById(R.id.titleText);
        body = findViewById(R.id.bodyText);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Post> call = apiService.getPostById(Integer.valueOf(getIntent().getStringExtra("id")));
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