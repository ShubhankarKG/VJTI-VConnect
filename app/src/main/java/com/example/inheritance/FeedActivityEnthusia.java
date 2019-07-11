package com.example.inheritance;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.inheritance.MainActivity.sharedPreferences;

public class FeedActivityEnthusia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Post> postList;
    private DatabaseReference mDatabase;
    private ProgressBar progressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_enthusia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.enthu_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList = new ArrayList<>();
        progressCircle = findViewById(R.id.progress_circle);

        mDatabase = FirebaseDatabase.getInstance().getReference("Enthusia");
        mDatabase.keepSynced(true);
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        Post post1 = dataSnapshot1.getValue(Post.class);
                        postList.add(post1);
                    }
                    adapter = new Adapter(FeedActivityEnthusia.this,postList);
                    recyclerView.setAdapter(adapter);
                    progressCircle.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FeedActivityEnthusia.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressCircle.setVisibility(View.INVISIBLE);

            }
        });


        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);


        if (sharedPreferences.getBoolean("logged", false) && sharedPreferences.getString("login_id", null).equals("admin@enthu")) {
            fabAdd.show();
        } else {
            fabAdd.hide();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(FeedActivityAero.this, AddPost.class);
                Toast.makeText(getApplicationContext(), "Adding a new post", Toast.LENGTH_SHORT).show();
                Intent addPost = new Intent(FeedActivityEnthusia.this, AddPost.class);
                addPost.putExtra("adminOf", "Enthusia");
                startActivity(addPost);
            }
        });
    }

}
