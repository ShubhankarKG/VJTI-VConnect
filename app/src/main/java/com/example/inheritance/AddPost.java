package com.example.inheritance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPost extends AppCompatActivity {

    EditText inputDate;
    EditText inputTitle;
    EditText inputDescription;
    String Date;
    String Title;
    String Description;

    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);
        inputDate = (EditText) findViewById(R.id.inputDate);
        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputDescription = (EditText) findViewById(R.id.inputDescription);
        Intent intent = getIntent();
        String committee = intent.getStringExtra("adminOf");
        dbRef = FirebaseDatabase.getInstance().getReference(committee);

        Button btnCreateProduct = findViewById(R.id.btnCreatePost);
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date = inputDate.getText().toString();
                Title = inputTitle.getText().toString();
                Description = inputDescription.getText().toString();


                if (!TextUtils.isEmpty(Title)) {
                    String id = dbRef.push().getKey();
                    Post post = new Post(Title, Description);
                    dbRef.child(id).setValue(post);
                    Toast.makeText(AddPost.this, "Post added successfully", Toast.LENGTH_SHORT); //Toast doesn't come!
                } else {
                    Toast.makeText(AddPost.this, "Please add a title", Toast.LENGTH_SHORT); //Toast doesn't come!
                }

            }

        });


    }
}
