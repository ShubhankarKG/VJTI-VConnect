package com.example.inheritance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditPost extends AppCompatActivity {
    EditText etPostTitle, etPostDescription;
    ImageView ivPost;
    Button bSave, bCancel, bLoad;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etPostTitle = (EditText) findViewById(R.id.etPostTitle);
        etPostDescription = (EditText) findViewById(R.id.etPostDescription);
        ivPost = (ImageView) findViewById(R.id.ivPost);
        bSave = (Button) findViewById(R.id.bSave);
        bCancel = (Button) findViewById(R.id.bCancel);
        bLoad = (Button) findViewById(R.id.bLoad);
        final String postTitle = etPostTitle.getText().toString();
        Intent intent = getIntent();
        final String committee = intent.getStringExtra("adminOf");
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditPost.this, FeedActivityAero.class));
                finish();
            }
        });


        bLoad.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference();
               Query query = databaseReference.child(committee).orderByChild("title").equalTo(postTitle);
                query .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            if(dataSnapshot1.child("title").toString().equals(postTitle)){
                                String postDescription = dataSnapshot1.child("description").getValue().toString();
                                etPostDescription.setText(postDescription);
                                String imageUrl = dataSnapshot1.child("image").getValue().toString();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
           }
       });


    }



}