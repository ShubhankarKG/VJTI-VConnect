package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddPost extends AppCompatActivity {

    String committee, Image, id, purpose, program, year, branch;
    EditText inputDate, inputTitle, inputDescription;
    String date, Title, Description, pathToFile;
    Button bPicture, bCamera;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int IMAGE_CAMERA_REQUEST = 30;
    ImageView ivPicture;
    DatabaseReference dbRef;
    Uri imageUri, file = null;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);
//        inputDate =  findViewById(R.id.inputDate);
        inputTitle =  findViewById(R.id.inputTitle);
        inputDescription =  findViewById(R.id.inputDescription);
        ivPicture =  findViewById(R.id.ivPicture);
//        inputDate = (EditText) findViewById(R.id.inputDate);
        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputDescription = (EditText) findViewById(R.id.inputDescription);
        ivPicture = (ImageView) findViewById(R.id.ivPicture);
        Button btnCreateProduct = findViewById(R.id.btnCreatePost);
        date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());

        Intent intent = getIntent();
        purpose = intent.getStringExtra("purpose");
        if (purpose.equals("post")) {
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


//        inputDate.setText(date);

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }

        });



        bPicture =  findViewById(R.id.bPicture);
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

        bCamera =  findViewById(R.id.bCamera);
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


    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST && data != null && data.getData() != null) {
                imageUri = data.getData();
                Picasso.get().load(imageUri).into(ivPicture);
            } else if (requestCode == IMAGE_CAMERA_REQUEST && data != null && data.getData() != null) {
                Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                ivPicture.setImageBitmap(bitmap);
                assert bitmap != null;
                imageUri = getImageUri(getApplicationContext(), bitmap);
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mp = MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile() {
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
                                    Image = uri.toString();
                                    Post post = new Post(Title, Description, Image, date);
                                    if (!TextUtils.isEmpty(id)) {
                                    } else {
                                        id = dbRef.push().getKey();
                                    }
                                    while (TextUtils.isEmpty(Title)) {
                                        Toast.makeText(AddPost.this, "Please add a title!", Toast.LENGTH_SHORT).show();
                                    }
                                    post.setId(id);
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

            Post post = new Post(Title, Description,date);
            if (!TextUtils.isEmpty(id)) {
            } else {
                id = dbRef.push().getKey();
            }
            post.setId(id);
            dbRef.child(id).setValue(post);
            Toast.makeText(AddPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddPost.this, Home.class));
        }

    }


    private Uri getImageUri(Context context, Bitmap inImage){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100 , bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, Title, null );
        return Uri.parse(path);
    }


}





