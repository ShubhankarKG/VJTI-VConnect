package com.example.inheritance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.inheritance.loginActivity.sharedPreferences;

public class FeedActivityAero extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Post> postList;

//    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_aero);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);


        recyclerView = (RecyclerView) findViewById(R.id.aero_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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


        if (sharedPreferences.getString("login_id", null).equals("admin@aero")) {
            fabAdd.show();
        } else {
            fabAdd.hide();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(FeedActivityAero.this, AddPost.class);
                Toast.makeText(getApplicationContext(), "Adding a new post", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
