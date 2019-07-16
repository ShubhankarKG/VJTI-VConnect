package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EditThisPost extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 10;
    public static final int IMAGE_CAMERA_REQUEST = 20;
    public String committee, Image, postId;
    EditText editDate, editTitle, editDescription;
    StorageReference storageReference;
    Button bCancel, bSave;
    String date, Title, Description;
    ImageView ivPost;
    Uri imageUri;
    DatabaseReference dbRef;
    Button bCamera, bPicture;

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

        date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());

        Intent intent = getIntent();
        committee = intent.getStringExtra("committee");
        postId = intent.getStringExtra("postID");

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditThisPost.this, MainActivity.class));
                finish();
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
                    if(!TextUtils.isEmpty(post.getImage())) {
                        Picasso.get().load(post.getImage()).into(ivPost);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.toException().toString());
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

        bPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri data = Uri.parse(pictureDirectoryPath);
                photoPickerIntent.setDataAndType(data, "image/*");
                startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
            }
        });

        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        bCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, IMAGE_CAMERA_REQUEST);
                }
            }
        });

        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mp = MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST && data != null && data.getData() != null) {
                imageUri = data.getData();
                Picasso.get().load(imageUri).into(ivPost);
            } else if (requestCode == IMAGE_CAMERA_REQUEST && data != null && data.getData() != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ivPost.setImageBitmap(bitmap);
            }
        }
    }

    private void uploadFile() {
        Title = editTitle.getText().toString();
        Description = editDescription.getText().toString();
        if (imageUri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Image = uri.toString();
                                    Post post = new Post(Title, Description, Image, date);
                                    if (!TextUtils.isEmpty(postId)) {
                                    } else {
                                        postId = dbRef.push().getKey();
                                    }
                                    while (TextUtils.isEmpty(Title)) {
                                        Toast.makeText(EditThisPost.this, "Please add a title!", Toast.LENGTH_SHORT).show();
                                    }
                                    post.setId(postId);
                                    dbRef.child(postId).setValue(post);
                                    Toast.makeText(EditThisPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(EditThisPost.this, MainActivity.class));
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
        } else {
        while (TextUtils.isEmpty(Title)) {
            Toast.makeText(EditThisPost.this, "Please add a title!", Toast.LENGTH_SHORT).show();
        }
        Post post = new Post(Title, Description, date);
        if (!TextUtils.isEmpty(postId)) {
        } else {
            postId = dbRef.push().getKey();
        }
        post.setId(postId);
        dbRef.setValue(post);
        Toast.makeText(EditThisPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditThisPost.this, MainActivity.class));
        }
    }
}

