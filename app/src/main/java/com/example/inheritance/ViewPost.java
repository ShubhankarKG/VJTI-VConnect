package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        final TextView vpPostTitle = findViewById(R.id.tvviewpost_title);
        final TextView vpPostDate = findViewById(R.id.tvviewpost_date);
        final TextView vpPostDescription = findViewById(R.id.tvviewpost_description);
        final ImageView vpImage = findViewById(R.id.ivviewpost_image);


  //      vpPostDate.setText(date);
        Intent intent = getIntent();
        String postID = intent.getStringExtra("postID");
        String committee = intent.getStringExtra("committee");
        if (postID.equals(null) || committee.equals(null))
            Toast.makeText(this, "PostID or commmittee error occured!", Toast.LENGTH_SHORT).show();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(committee).child(postID);
        dbRef.keepSynced(true);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Post post = dataSnapshot.getValue(Post.class);
                    assert post != null;
                    vpPostTitle.setText(post.getTitle());
                    vpPostDate.setText(post.getDate());
                    vpPostDescription.setText(post.getDescription());
                    if(!TextUtils.isEmpty(post.getImage())){
                        Picasso.get()
                                .load(post.getImage())
                                .into(vpImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.toException().toString());
            }
        });


    }
}
