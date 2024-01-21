package com.example.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

//        List<Post> pl = new ArrayList<>();
//        pl.add(new Post(1,1,"One","Two"));
//        pl.add(new Post(2,3,"three","four"));
//        Log.d("myPosts",String.valueOf(pl.size()));
//        CustomAdapter adapter = new CustomAdapter(this,pl);
//        listView.setAdapter(adapter);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Post>> call = apiService.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    CustomAdapter adapter = new CustomAdapter(getApplicationContext(),posts);
                    listView.setAdapter(adapter);
                } else {
                    // Handle errors
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Handle failure
            }
        });

    }

    public class CustomAdapter extends BaseAdapter {
        private Context context;
        private List<Post> itemList;
        public CustomAdapter(Context context, List<Post> itemList) {
            this.context = context;
            this.itemList = itemList;
        }

        @Override
        public CharSequence[] getAutofillOptions() {
            return super.getAutofillOptions();
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.custom_list_item, parent, false);
            }

            final Post currentItem = (Post) getItem(position);

            TextView titleText = convertView.findViewById(R.id.titleText);
            TextView bodyText = convertView.findViewById(R.id.bodyText);

            titleText.setText(currentItem.getTitle());
            bodyText.setText(String.valueOf(currentItem.getBody()));

            convertView.setOnClickListener( View -> {
                        // Show ID in a Toast message
                Intent i = new Intent(getApplicationContext(),ShowActivity.class);
                i.putExtra("id",String.valueOf(currentItem.getId()));
                startActivity(i);
                    }
            );

            return convertView;
        }

    }

}