package com.example.inheritance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.inheritance.MainActivity.sharedPreferences;

public class FeedActivityAero extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Post> postList = new ArrayList<>();
    ProgressBar progressBar;
    String url_get_all_posts = "https://skgrocks.000webhostapp.com/connect/get_all_posts.php";

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


        if (sharedPreferences.getBoolean("logged", false) && sharedPreferences.getString("login_id", null).equals("admin@aero")) {

            fabAdd.show();


        } else {
            fabAdd.hide();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Adding a new post", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FeedActivityAero.this, AddNewPost.class));
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.Item1 :
                Toast.makeText(getApplicationContext(), "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Item2 :
                Toast.makeText(getApplicationContext(), "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
