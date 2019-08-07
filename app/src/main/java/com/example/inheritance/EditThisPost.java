package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.CaseMap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class EditThisPost extends AppCompatActivity {

    String userChoosenTask;

    public static final int IMAGE_GALLERY_REQUEST = 10;
    public static final int IMAGE_CAMERA_REQUEST = 20;
    public String committee, Image, postId;
    EditText editDate, editTitle, editDescription;
    Button bCancel, bSave, bRemove;
    public Boolean isEmpty, oldImage,
            isOldEmpty = false;
    ImageView ivPost;
    Uri oldImageUri, newImageUri;
    DatabaseReference dbRef;
    Button bCamera, bPicture;
    FirebaseStorage firebaseStorage;
    String imageUrl;
    StorageReference storageReference, oldStorageReference;
    String date, Title, Description, oldImageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_this_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        editDate = (EditText) findViewById(R.id.inputDate);
        editTitle =  findViewById(R.id.inputTitle);
        editDescription =  findViewById(R.id.inputDescription);
        ivPost =  findViewById(R.id.ivPicture);
        bSave =  findViewById(R.id.bSave);
        bCancel =  findViewById(R.id.bCancel);
        bCamera = findViewById(R.id.bCamera);
        bPicture = findViewById(R.id.bPicture);
        bRemove = findViewById(R.id.bRemove);


        date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());

        Intent intent = getIntent();
        committee = intent.getStringExtra("committee");
        postId = intent.getStringExtra("postID");

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(EditThisPost.this, Feed.class);
                goBack.putExtra("committee", committee);
                startActivity(goBack);
            }
        });

        if ((postId == null) || (committee == null))
            Toast.makeText(this, "PostID or commmittee error occured!", Toast.LENGTH_SHORT).show();
        dbRef = FirebaseDatabase.getInstance().getReference(committee).child(postId);
        dbRef.keepSynced(true);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    assert post != null;
                    editTitle.setText(post.getTitle());
//                    editDate.setText(post.getDate());
                    editDescription.setText(post.getDescription());
                    if(!TextUtils.isEmpty(post.getImageUrl())) {
                        oldImageString = post.getImageUrl();
                        Picasso.get().load(post.getImageUrl()).into(ivPost);
                        isEmpty = false;
                        oldImage = true;
                        imageUrl = post.getImageUrl();
                    }else {
                        isEmpty = true;
                        isOldEmpty = true;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.toException().toString());
            }
        });

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference(committee);


        bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPost.setImageResource(0);
                isEmpty = true;
            }
        });

        bPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryUpload();
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST){
                if (data != null) {
                    newImageUri = data.getData();
                    Picasso.get().load(newImageUri).into(ivPost);
                    isEmpty = false;
                    oldImage = false;
                }
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mp = MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(uri));
    }



    private void galleryUpload(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_GALLERY_REQUEST);
    }


    private void uploadFile(){

        if(isEmpty){
//            Post post = new Post(Title, Description,date);
//            if (!TextUtils.isEmpty(postId)) {
//            } else {
//                postId = dbRef.push().getKey();
//            }
//            post.setId(postId);
//            dbRef.child(postId).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(EditThisPost.this, "Update Successful", Toast.LENGTH_SHORT).show();
//                }
//            });
            Title = editTitle.getText().toString();
            Description = editDescription.getText().toString();
            Post post = new Post(Title, Description, date);
            dbRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(EditThisPost.this, "Done!", Toast.LENGTH_SHORT).show();
                }
            });
            Intent goBack = new Intent(EditThisPost.this, Feed.class);
            goBack.putExtra("committee", committee);
            startActivity(goBack);

        }
        else{


            if(oldImage){
                Title = editTitle.getText().toString();
                Description = editDescription.getText().toString();
//                dbRef.child("title").setValue(Title);
//                dbRef.child("description").setValue(Description);
//                dbRef.child("date").setValue(date);
//                dbRef.child("id").setValue(postId);
                Post post = new Post(Title, Description, oldImageString);
                dbRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditThisPost.this, "Update successful!", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent goBack = new Intent(EditThisPost.this, Feed.class);
                goBack.putExtra("committee", committee);
                startActivity(goBack);
            }
            else{
                dbRef.child("image").removeValue();
                if(newImageUri!= null){
                    final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(newImageUri));
                    fileReference.putFile(newImageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Title = editTitle.getText().toString();
                                            Description = editDescription.getText().toString();
                                            Image = uri.toString();
                                            Post post = new Post(Title, Description, Image, date);
                                            dbRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(EditThisPost.this, "Update Successful", Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                            Intent goBack = new Intent(EditThisPost.this, Feed.class);
                                            goBack.putExtra("committee", committee);
                                            startActivity(goBack);

                                        }
                                    });

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(EditThisPost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        }

        if (!isOldEmpty) {
            oldStorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl); //Causes Runtime error!!
            oldStorageReference.delete();
        }
    }


}



