package com.example.inheritance;
// test file
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final TextView vpPostTitle = (TextView) findViewById(R.id.tvviewpost_title);
        final TextView vpPostDate = (TextView) findViewById(R.id.tvviewpost_date);
        final TextView vpPostDescription = (TextView) findViewById(R.id.tvviewpost_description);
        //     ImageView vpImage = (ImageView) findViewById(R.id.ivviewpost_image);
        //      vpPostDate.setText(date);
       // Intent intent = getIntent();
       // String postID = intent.getStringExtra("postID");
        //String committee = intent.getStringExtra("committee");
        String postID = "-Lj0wApIMkB4B9HBRxdI";
        String committee = "AeroVJTI";
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(committee).child(postID);
        dbRef.keepSynced(true);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Post post = dataSnapshot.getValue(Post.class);
                    vpPostTitle.setText(post.getTitle());
                    vpPostDate.setText(post.getDate());
                    vpPostDescription.setText(post.getDescription());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
