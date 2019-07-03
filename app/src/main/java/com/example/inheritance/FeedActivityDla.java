package com.example.inheritance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.inheritance.MainActivity.sharedPreferences;

public class FeedActivityDla extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_dla);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.dla_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);

        postList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Post post = new Post(
                    "Post Title " + (i + 1),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            );

            postList.add(post);
        }

        adapter = new Adapter(postList, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);


        if (sharedPreferences.getBoolean("logged", false) && sharedPreferences.getString("login_id", null).equals("admin@dla")) {
            fabAdd.show();
        } else {
            fabAdd.hide();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(FeedActivityAero.this, AddPost.class);
                Toast.makeText(getApplicationContext(), "Adding a new post", Toast.LENGTH_SHORT).show();
                Intent addPost = new Intent(FeedActivityDla.this, AddPost.class);
                addPost.putExtra("adminOf", "DLA");
                startActivity(addPost);
            }
        });
    }

}
