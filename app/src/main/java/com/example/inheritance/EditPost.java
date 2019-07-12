package com.example.inheritance;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class EditPost extends AppCompatActivity {
    public static final int IMAGE_GALLERY_REQUEST = 10;
    public static final int IMAGE_CAMERA_REQUEST = 20;

    EditText etPostTitle, etPostDescription;
    ImageView ivPost;
    Button bSave, bCancel, bLoad;
    DatabaseReference databaseReference;
    String postTitle, postDescription, Image, date, id;
    StorageReference storageReference;
    Uri imageUri;

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
        postTitle = etPostTitle.getText().toString();
        date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());

        Intent intent = getIntent();
        final String committee = intent.getStringExtra("adminOf");

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditPost.this, MainActivity.class));
                finish();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("COC");


        bLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFile();
            }
        });


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });


        registerForContextMenu(ivPost);

    }

    private void loadFile() {
        final String Title = postTitle.toUpperCase();
        Query query = databaseReference.orderByChild("title").equalTo(Title);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.child("title").toString().equals(Title)) {
                        String postDescription = dataSnapshot1.child("description").getValue().toString();
                        etPostDescription.setText(postDescription);
                        String imageUrl = dataSnapshot1.child("image").getValue().toString();
                        Picasso.get().load(imageUrl).into(ivPost);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mp = MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Image From");
        menu.add(0, v.getId(), 0, "GALLERY");
        menu.add(0, v.getId(), 0, "CAMERA");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "GALLERY") {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String pictureDirectoryPath = pictureDirectory.getPath();
            Uri data = Uri.parse(pictureDirectoryPath);
            photoPickerIntent.setDataAndType(data, "image/*");
            startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
        } else if (item.getTitle() == "CAMERA") {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, IMAGE_CAMERA_REQUEST);
            }
        }
        return true;
    }

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
        postTitle = etPostTitle.getText().toString();
        postDescription = etPostDescription.getText().toString();
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
                                    Post post = new Post(postTitle, postDescription, Image, date);
                                    if (!TextUtils.isEmpty(id)) {
                                    } else {
                                        id = databaseReference.push().getKey();
                                    }
                                    databaseReference.child(id).setValue(post);
                                    Toast.makeText(EditPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(EditPost.this, MainActivity.class));
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditPost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }


    }


}

