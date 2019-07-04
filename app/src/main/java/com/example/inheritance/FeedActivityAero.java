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

import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import static com.example.inheritance.MainActivity.sharedPreferences;

public class FeedActivityAero extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Post> postList = new ArrayList<>();
    ProgressBar progressBar;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_aero);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("inheritance-e0452").child("AeroVjti");
        mFirebaseDatabase.keepSynced(true);

        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);


        recyclerView = (RecyclerView) findViewById(R.id.aero_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
//                startActivity(new Intent(FeedActivityAero.this, AddNewPost.class));
                Intent addPost = new Intent(FeedActivityAero.this, AddPost.class);
                addPost.putExtra("adminOf", "AeroVJTI");
                startActivity(addPost);
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

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Post, PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>
                (Post.class, R.layout.feed_posts, PostViewHolder.class, mFirebaseDatabase) {
            @Override
            protected void populateViewHolder(PostViewHolder postViewHolder, Post post, int i) {
                postViewHolder.setTitle(post.getTitle());
                postViewHolder.setDescription(post.getDescription());
                postViewHolder.setImage(getApplicationContext(), post.getImage());
            }
        };
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public PostViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title){
            TextView textView = (TextView)mView.findViewById(R.id.post_title);
            textView.setText(title);
        }

        public void setDescription(String description){
            TextView textView = (TextView)mView.findViewById(R.id.post_description);
            textView.setText(description);
        }

        public void setImage(Context ctx, String image){
            ImageView imageView = (ImageView)mView.findViewById(R.id.ivPost);
            Picasso.with(ctx).load(image).into(imageView);
        }

    }

}
