package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPost extends AppCompatActivity {

    //  Variable declarations

    String committee, imageUrl, id, purpose, program, year, branch;
    public EditText inputTitle, inputDescription;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public String date, Title, Description;
    public Button bPicture, btnCreatePost;
    ProgressDialog progressDialog;
    public ImageView ivPicture;
    DatabaseReference dbRef;
    Uri imageUri;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        inputTitle =  findViewById(R.id.inputTitle);
        inputDescription =  findViewById(R.id.inputDescription);
        ivPicture =  findViewById(R.id.ivPicture);

        btnCreatePost = findViewById(R.id.btnCreatePost);
        bPicture =  findViewById(R.id.bPicture);

        DateFormat Date = new SimpleDateFormat("EEE, MMM d, ''yy");
        date = Date.format(new Date());
        // Check whether intent is for student activity or notices.
        Intent intent = getIntent();
        purpose = intent.getStringExtra("purpose");
        assert purpose != null;
        if (purpose.equals("student_activity")) {
            committee = intent.getStringExtra("adminOf");
            dbRef = FirebaseDatabase.getInstance().getReference(committee);
            storageReference = FirebaseStorage.getInstance().getReference(committee);
        } else if (purpose.equals("notice")) {
            program = intent.getStringExtra("program");
            branch = intent.getStringExtra("branch");
            year = intent.getStringExtra("year");
            if (!program.equals("MCA")) {
                dbRef = FirebaseDatabase.getInstance().getReference(program).child(branch).child(year);
                storageReference = FirebaseStorage.getInstance().getReference(program).child(branch).child(year);
            } else {
                dbRef = FirebaseDatabase.getInstance().getReference(program).child(year);
                storageReference = FirebaseStorage.getInstance().getReference(program).child(year);
            }
        }

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadPost();
            }

        });

        // Check permissions before continuing
        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }


        bPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
            }
        });

    }

    private void uploadPost() {
        Title = inputTitle.getText().toString();
        Description = inputDescription.getText().toString();
        if (imageUri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imageUrl = uri.toString();
                                    Post post = new Post(Title, Description, date);
                                    if (TextUtils.isEmpty(id)) {
                                        id = dbRef.push().getKey();
                                    }
                                    while (TextUtils.isEmpty(Title)) {
                                        Toast.makeText(AddPost.this, "Please add a title!", Toast.LENGTH_SHORT).show();
                                    }
                                    post.setId(id);
                                    post.setImageUrl(imageUrl);
                                    dbRef.child(id).setValue(post);
                                    Toast.makeText(AddPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddPost.this, Home.class));
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddPost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {

            Post post = new Post(Title, Description, date);
            if (TextUtils.isEmpty(id)) {
                id = dbRef.push().getKey();
            }
            post.setId(id);
            dbRef.child(id).setValue(post);
            Toast.makeText(AddPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddPost.this, Home.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST && data != null && data.getData() != null) {
                imageUri = data.getData();
                Picasso.get().load(imageUri).into(ivPicture);
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mp = MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(uri));
    }

}





