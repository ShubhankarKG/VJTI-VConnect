package com.example.inheritance;
// test file
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import static com.example.inheritance.MainActivity.sharedPreferences;

public class Main2Activity extends AppCompatActivity {
    private List<Post> postData;
    public RecyclerView mPostList;
    private DatabaseReference mDatabase;
    private Adapter adapter;
    private String COMMITTEE ;
    final String USERCRED = "admin@aero";
    private ProgressBar progressCircle;

    TextView tvHeading;
    TextView tvTagline;
    ImageView ivFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);

        tvHeading = findViewById(R.id.tvHeading);
        tvTagline = findViewById(R.id.tvTagline);
        ivFeed = findViewById(R.id.ivFeed_logo);

        mPostList = findViewById(R.id.recyclerview);
        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(new LinearLayoutManager(this));

        progressCircle = findViewById(R.id.progress_circle);
        postData = new ArrayList<>();

        Intent intent = getIntent();
        String committee = intent.getStringExtra("committee");

        switch (committee) {
            case "AeroVjti":
                tvHeading.setText("AeroVJTI");
                tvTagline.setText("Born to fly");
                ivFeed.setImageResource(R.drawable.aerovjti);
                break;
            case "COC":
                tvHeading.setText("COC");
                tvTagline.setText("Imagine, Believe, Achieve");
                ivFeed.setImageResource(R.drawable.coc);
                break;
            case "DLA":
                tvHeading.setText("DLA");
                tvTagline.setText("");
                ivFeed.setImageResource(R.drawable.dla);
                break;
            case "ECell":
                tvHeading.setText("Entrepreneurship Cell");
                tvTagline.setText("");
                ivFeed.setImageResource(R.drawable.ecell);
                break;
            case "Enthusia":
                tvHeading.setText("Enthusia");
                tvTagline.setText("");
                ivFeed.setImageResource(R.drawable.enthu);
                break;
            case "IEEE":
                tvHeading.setText("IEEE VJTI");
                ivFeed.setImageResource(R.drawable.ieeesq);
                break;
            case "Pratibimb":
                tvHeading.setText("Pratibimb");
                ivFeed.setImageResource(R.drawable.prati);
                break;
            case "VJTI Racing":
                tvHeading.setText("VJTI Racing");
                ivFeed.setImageResource(R.drawable.racing);
                break;
            case "Rangawardhan":
                tvHeading.setText("Rangawardhan");
                ivFeed.setImageResource(R.drawable.ranga);
                break;
            case "SRA":
                tvHeading.setText("Society of Robotics and Automation");
                ivFeed.setImageResource(R.drawable.sra);
                break;
            case "Swachh VJTI":
                tvHeading.setText("Swachh Vjti");
                ivFeed.setImageResource(R.drawable.swachh);
                break;
            case "Technovanza":
                tvHeading.setText("Technovanza");
                ivFeed.setImageResource(R.drawable.techno);
                break;
            case "VJTI Alumni":
                tvHeading.setText("VJTI Alumni Association");
                ivFeed.setImageResource(R.drawable.alumni);
                break;
        }

        mDatabase = FirebaseDatabase.getInstance().getReference(committee);
        mDatabase.keepSynced(true);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postData.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Post post1 = dataSnapshot1.getValue(Post.class);
                        postData.add(post1);
                    }
                    adapter = new Adapter(Main2Activity.this, postData, COMMITTEE, USERCRED);
                    mPostList.setAdapter(adapter);


                }
                progressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressCircle.setVisibility(View.INVISIBLE);
            }
        });

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        if (sharedPreferences.getBoolean("logged", false) && sharedPreferences.getString("login_id", null).equals(USERCRED)) {

            fabAdd.show();  //show FAB for adding post to admin


        } else {
            fabAdd.hide();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Adding a new post", Toast.LENGTH_SHORT).show();
                Intent addPost = new Intent(Main2Activity.this, AddPost.class);
                addPost.putExtra("adminOf", "AeroVJTI");
                startActivity(addPost);
            }
        });

    }
}
