package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.inheritance.Home.sharedPreferences;

public class Feed extends AppCompatActivity {
    //Variable declarations
    public RecyclerView mPostList;
    TextView tvHeading;
    TextView tvTagline;
    ImageView ivLogo;
    private List<Post> postData;
    private Adapter adapter;
    private String committee;
    private String adminCred;
    private ProgressBar progressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        tvHeading =  findViewById(R.id.heading);
        tvTagline =  findViewById(R.id.tagline);
        ivLogo =  findViewById(R.id.feed_logo);

        mPostList =  findViewById(R.id.feed_recyclerview);
        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(new LinearLayoutManager(this));

        progressCircle =  findViewById(R.id.progress_circle);
        postData = new ArrayList<>();

        Intent intent = getIntent();
        committee = intent.getStringExtra("committee");

        if (committee != null) {
            switch (committee) {
                case "AeroVJTI":
                    tvHeading.setText(R.string.AeroVjti);
                    tvTagline.setText(R.string.AeroVjti_tagline);
                    ivLogo.setImageResource(R.drawable.aerovjti);
                    adminCred = "admin@aero";
                    break;
                case "COC":
                    tvHeading.setText(R.string.COC);
                    tvTagline.setText(R.string.COC_tagline);
                    ivLogo.setImageResource(R.drawable.coc);
                    adminCred = "admin@coc";
                    break;
                case "DLA":
                    tvHeading.setText(R.string.DLA);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.dla);
                    adminCred = "admin@dla";
                    break;
                case "ECell":
                    tvHeading.setText(R.string.ECell);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.ecell);
                    adminCred = "admin@ecell";
                    break;
                case "Enthusia":
                    tvHeading.setText(R.string.Enthusia);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.enthu);
                    adminCred = "admin@enthu";
                    break;
                case "IEEE":
                    tvHeading.setText(R.string.IEEE);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.ieeesq);
                    adminCred = "admin@ieee";
                    break;
                case "Pratibimb":
                    tvHeading.setText(R.string.Pratibimb);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.prati);
                    adminCred = "admin@prati";
                    break;
                case "VJTI Racing":
                    tvHeading.setText(R.string.VJTI_Racing);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.racing);
                    adminCred = "admin@racing";
                    break;
                case "Rangawardhan":
                    tvHeading.setText(R.string.Rangawardhan);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.ranga);
                    adminCred = "admin@ranga";
                    break;
                case "SRA":
                    tvHeading.setText(R.string.SRA);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.sra);
                    adminCred = "admin@sra";
                    break;
                case "Swachh VJTI":
                    tvHeading.setText(R.string.Swachh_VJTI);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.swachh);
                    adminCred = "admin@swachh";
                    break;
                case "Technovanza":
                    tvHeading.setText(R.string.Technovanza);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.techno);
                    adminCred = "admin@techno";
                    break;
                case "VJTI Alumni":
                    tvHeading.setText(R.string.Alumni);
                    tvTagline.setText("");
                    ivLogo.setImageResource(R.drawable.alumni);
                    adminCred = "admin@alumni";
                    break;
            }
        }

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference dbRef = firebaseDatabase.getReference(committee);
        dbRef.keepSynced(true);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postData.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Post post1 = dataSnapshot1.getValue(Post.class);
                        postData.add(post1);
                    }
                    adapter = new Adapter(Feed.this, postData, committee, adminCred);
                    mPostList.setAdapter(adapter);
                }
                progressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Feed.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressCircle.setVisibility(View.INVISIBLE);

            }
        });


        FloatingActionButton fabAdd =  findViewById(R.id.fabAdd);

        if (sharedPreferences.getBoolean("logged", false) && Objects.equals(sharedPreferences.getString("login_id", null), adminCred)) {

            fabAdd.show();  //show FAB for adding post to admin


        } else {
            fabAdd.hide();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Adding a new post", Toast.LENGTH_SHORT).show();
                Intent addPost = new Intent(Feed.this, AddPost.class);
                addPost.putExtra("adminOf", committee);
                addPost.putExtra("purpose", "student_activity");
                startActivity(addPost);
            }
        });
    }

}

