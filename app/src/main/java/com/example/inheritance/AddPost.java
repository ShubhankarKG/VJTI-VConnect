package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    public static final int ACTION_PDF = 10;
    public static final int ACTION_PIC = 20;
    String committee, imageUrl, pdfUrl, id, purpose, program, year, branch;
    EditText inputTitle, inputDescription;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int IMAGE_CAMERA_REQUEST = 30;
    String date, title, description, pathToFile;
    Button bPicture, bCamera, bSelectPdf;
    ProgressDialog progressDialog;
    ImageView ivPicture;
    DatabaseReference dbRef;
    private TextView tvStatus;
    private Uri pdfUri, picUri;
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
        Button btnCreatePost = findViewById(R.id.btnCreatePost);
        bSelectPdf = (Button) findViewById(R.id.bSelectPdf);
        tvStatus = findViewById(R.id.tvStatus);

        date = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(new Date());


        Intent intent = getIntent();
        purpose = intent.getStringExtra("purpose");
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


//        inputDate.setText(date);

        bSelectPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddPost.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                } else {
                    ActivityCompat.requestPermissions(AddPost.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 30);
                }
            }
        });

//        bUploadPdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(pdfUri!= null){
//                    uploadFile();
//                }
//            }
//        });

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPost();
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

    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, ACTION_PDF);
    }

//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK){
//            if(requestCode == ACTION_PDF && data!= null){
//                pdfUri = data.getData();
//                tvStatus.setText("File selected : " + data.getData().getLastPathSegment());
//            }
//        }
//    }


    //  THIS!
    private void uploadPost() {
        progressDialog = new ProgressDialog(AddPost.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading post...");
        progressDialog.setProgress(0);
        progressDialog.show();
        boolean pdfUp, imageUp;
        title = inputTitle.getText().toString();
        if (pdfUri != null) {
            storageReference.putFile(pdfUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.w("myTag", "File uploaded successfully");

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    pdfUrl = uri.toString();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddPost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setProgress(currentProgress);

                }
            });
        }
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
        }

        description = inputDescription.getText().toString();
        Post post = new Post(title, description, imageUrl, date, pdfUrl);
        if (TextUtils.isEmpty(id)) {
            id = dbRef.push().getKey();
        }
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Please add a title", Toast.LENGTH_SHORT).show();
        }
        post.setId(id);
        dbRef.child(id).setValue(post);
        Toast.makeText(AddPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
        Intent goBack = new Intent(AddPost.this, Home.class);
        startActivity(goBack);
    }

    @SuppressLint("SetTextI18n")
    @Override
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
            } else if (requestCode == ACTION_PDF && data != null && data.getData() != null) {
                pdfUri = data.getData();
                tvStatus.setText("File selected : " + data.getData().getLastPathSegment());
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mp = MimeTypeMap.getSingleton();
        return mp.getExtensionFromMimeType(cr.getType(uri));
    }

    /*
    private void uploadFile() {
        title = inputTitle.getText().toString();
        description = inputDescription.getText().toString();
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
                                    Post post = new Post(title, description, Image, date);
                                    if (!TextUtils.isEmpty(id)) {
                                    } else {
                                        id = dbRef.push().getKey();
                                    }
                                    while (TextUtils.isEmpty(title)) {
                                        Toast.makeText(AddPost.this, "Please add a title!", Toast.LENGTH_SHORT).show();
                                    }
                                    post.setId(id);
                                    dbRef.child(id).setValue(post);
                                    Toast.makeText(AddPost.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddPost.this, Home.class));
                                } //HERE
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

            Post post = new Post(title, description,date);
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

*/
    private Uri getImageUri(Context context, Bitmap inImage){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100 , bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, title, null);
        return Uri.parse(path);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();
        } else {
            Toast.makeText(AddPost.this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }
    }

}





