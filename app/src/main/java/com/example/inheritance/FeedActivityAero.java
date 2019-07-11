package com.example.inheritance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import static com.example.inheritance.MainActivity.sharedPreferences;

public class FeedActivityAero extends AppCompatActivity {

    private List<Post> postData;
    private RecyclerView mPostList;
    private DatabaseReference mDatabase;
    private Adapter adapter;
    final String COMMITTEE = "AeroVJTI";
    final String USERCRED = "admin@aero";
    private ProgressBar progressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feed_aero);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);


        mPostList = (RecyclerView) findViewById(R.id.aero_recyclerview);
        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(new LinearLayoutManager(this));

        progressCircle = findViewById(R.id.progress_circle);
        postData = new ArrayList<>();


        Intent intent = getIntent();
        String committee = intent.getStringExtra("adminOf");


        mDatabase = FirebaseDatabase.getInstance().getReference("AeroVJTI");
        mDatabase.keepSynced(true);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Post post1 = dataSnapshot1.getValue(Post.class);
                        postData.add(post1);
                    }
                    adapter = new Adapter(FeedActivityAero.this, postData, COMMITTEE, USERCRED);
                    mPostList.setAdapter(adapter);
                    progressCircle.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FeedActivityAero.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressCircle.setVisibility(View.INVISIBLE);

            }
        });


        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);

        if (sharedPreferences.getBoolean("logged", false) && sharedPreferences.getString("login_id", null).equals(USERCRED)) {

            fabAdd.show();  //show FAB for adding post to admin


        } else {
            fabAdd.hide();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Adding a new post", Toast.LENGTH_SHORT).show();
                Intent addPost = new Intent(FeedActivityAero.this, AddPost.class);
                addPost.putExtra("adminOf", "AeroVJTI");
                startActivity(addPost);
            }
        });

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity1, menu);
        return true;
    }
    */
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Item1:
                Intent intent = new Intent(FeedActivityAero.this, EditPost.class);
                intent.putExtra("adminOf", "AeroVJTI");
                startActivity(intent);
                return true;
            case R.id.Item2:
                Intent intent1 = new Intent(FeedActivityAero.this, DeleteActivity.class);
                intent1.putExtra("adminOf", "AeroVJTI");
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/
}

