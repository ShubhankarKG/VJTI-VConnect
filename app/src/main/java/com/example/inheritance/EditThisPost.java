package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EditThisPost extends AppCompatActivity {
    // Variable declarations
    public static final int IMAGE_GALLERY_REQUEST = 10;
    public String committee, Image, postId, program, branch, year;
    EditText editTitle, editDescription;
    Button bCancel, bSave, bRemove;
    public Boolean isEmpty, oldImage,
            isOldEmpty = false;
    ImageView ivPost;
    Uri newImageUri;
    DatabaseReference dbRef;
    Button bPicture;
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

        editTitle =  findViewById(R.id.inputTitle);
        editDescription =  findViewById(R.id.inputDescription);

        ivPost =  findViewById(R.id.ivPicture);

        bSave =  findViewById(R.id.bSave);
        bCancel =  findViewById(R.id.bCancel);
        bPicture = findViewById(R.id.bPicture);
        bRemove = findViewById(R.id.bRemove);

        date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());

        Intent intent = getIntent();
        postId = intent.getStringExtra("postID");

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(EditThisPost.this, Feed.class);
                goBack.putExtra("committee", committee);
                startActivity(goBack);
            }
        });

        if ((postId == null))
            Toast.makeText(this, "PostID error occured!", Toast.LENGTH_SHORT).show();
        else if (Objects.equals(intent.getStringExtra("purpose"), "student_activity")) {
            committee = intent.getStringExtra("committee");
            dbRef = FirebaseDatabase.getInstance().getReference(committee).child(postId);
            dbRef.keepSynced(true);
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Post post = dataSnapshot.getValue(Post.class);
                        assert post != null;
                        editTitle.setText(post.getTitle());
                        editDescription.setText(post.getDescription());
                        if (!TextUtils.isEmpty(post.getImageUrl())) {
                            oldImageString = post.getImageUrl();
                            Picasso.get().load(post.getImageUrl()).into(ivPost);
                            isEmpty = false;
                            oldImage = true;
                            imageUrl = post.getImageUrl();
                        } else {
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
        } else if (Objects.equals(intent.getStringExtra("purpose"), "notice")) {
            program = intent.getStringExtra("program");
            branch = intent.getStringExtra("branch");
            year = intent.getStringExtra("year");
            if (!program.equals("MCA")) {
                dbRef = FirebaseDatabase.getInstance().getReference(program).child(branch).child(year).child(postId);
            } else {  // MCA Condition
                dbRef = FirebaseDatabase.getInstance().getReference(program).child(year).child(postId);
            }
            dbRef.keepSynced(true);
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Post post = dataSnapshot.getValue(Post.class);
                        assert post != null;
                        editTitle.setText(post.getTitle());
                        editDescription.setText(post.getDescription());
                        if (!TextUtils.isEmpty(post.getImageUrl())) {
                            oldImageString = post.getImageUrl();
                            Picasso.get().load(post.getImageUrl()).into(ivPost);
                            isEmpty = false;
                            oldImage = true;
                            imageUrl = post.getImageUrl();
                        } else {
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
            if (!program.equals("MCA")) {
                storageReference = firebaseStorage.getReference(program).child(branch).child(year);
            } else {
                storageReference = firebaseStorage.getReference(program).child(year);
            }
        }

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
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }


    private void uploadFile(){

        if(isEmpty){
            Title = editTitle.getText().toString();
            Description = editDescription.getText().toString();
            Post post = new Post(Title, Description, date);
            post.setId(postId);
            Objects.requireNonNull(dbRef.getParent()).child(postId).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                Post post = new Post(Title, Description, oldImageString, date);
                post.setId(postId);
                Objects.requireNonNull(dbRef.getParent()).child(postId).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                                            post.setId(postId);
                                            Objects.requireNonNull(dbRef.getParent()).child(postId).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
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
            oldStorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl);
            oldStorageReference.delete();
        }
    }


}



