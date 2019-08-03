package com.example.inheritance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Objects;

public class AddNotice extends AppCompatActivity {

    public static final int ACTION_PDF = 10;
    public static final int ACTION_PIC = 20;
    private Button bPdf, bPic , bUpload;
    private FirebaseStorage storage;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private Uri pdfUri, picUri;
    private EditText etDescription, etTitle, etFileName;
    private ProgressDialog progressBar;
    public String id;
    private TextView tvStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bPdf = findViewById(R.id.buttonUploadPdf);
        bPic = findViewById(R.id.btnUploadPic);
        bUpload = findViewById(R.id.btnUpload);

        etDescription = findViewById(R.id.editTextDescription);
        etTitle = findViewById(R.id.etTitle);
        etFileName = findViewById(R.id.editTextFileName);

        tvStatus = findViewById(R.id.tvStatus);

        storage = FirebaseStorage.getInstance();
        mStorage = storage.getReference("batch");
        mDatabase = FirebaseDatabase.getInstance().getReference("Btech");

        bPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(AddNotice.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    uploadPdf();
                }
                else {
                    ActivityCompat.requestPermissions(AddNotice.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 30);
                }
            }
        });

        bPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(AddNotice.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    uploadPic();
                }
                else {
                    ActivityCompat.requestPermissions(AddNotice.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 30);
                }
            }
        });

        bUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri!= null){
                    uploadFile(pdfUri);
                }
            }
        });

    }

    private void uploadPdf(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, ACTION_PDF);
    }

    private void uploadPic(){
        Intent intent = new Intent();
        intent.setType("images/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, ACTION_PIC);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == ACTION_PDF && data!= null){
                pdfUri = data.getData();
                tvStatus.setText("A file is selected : " + data.getData().getLastPathSegment());
            }
        }
    }

    private void uploadFile(Uri uri){
        progressBar = new ProgressDialog(AddNotice.this);
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setTitle("Uploading file...");
        progressBar.setProgress(0);
        progressBar.show();

        String fileName = etFileName.getText().toString();
        mStorage.child("Batch").child(fileName).putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                String title = etTitle.getText().toString().trim();
                                String description = etDescription.getText().toString();
                                Upload upload = new Upload(url, title, description);
                                if(!TextUtils.isEmpty(id)){

                                }
                                else {
                                    id = mDatabase.push().getKey();
                                }
                                upload.setId(id);
                                mDatabase.child(id).setValue(upload)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                progressBar.dismiss();
                                                Toast.makeText(AddNotice.this, "File uploaded successfullt", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNotice.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressBar.setProgress(currentProgress);

                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 10 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            uploadPdf();
        }
        else {
            Toast.makeText(AddNotice.this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }
    }
}
