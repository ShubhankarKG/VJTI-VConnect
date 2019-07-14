package com.example.inheritance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



//import org.apache.http.NameValuePair;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddNewPost extends AppCompatActivity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int IMAGE_CAMERA_REQUEST = 30;
    EditText inputDate;
    EditText inputTitle;
    EditText inputDescription;
    String Date;
    String Title;
    String Description;
    HashMap<String,String> hashMap = new HashMap<>();
    ImageView ivPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputDate = findViewById(R.id.inputDate);
        inputTitle = findViewById(R.id.inputTitle);
        inputDescription = findViewById(R.id.inputDescription);
        Button btnCreateProduct = findViewById(R.id.btnCreatePost);
        ivPicture = findViewById(R.id.ivPicture);

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date = inputDate.getText().toString();
                Title = inputTitle.getText().toString();
                Description = inputDescription.getText().toString();

                Button bPicture = findViewById(R.id.bPicture);
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

                Button bCamera = findViewById(R.id.bCamera);
                bCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, IMAGE_CAMERA_REQUEST);
                    }
                });


            }


            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                    if (requestCode == IMAGE_GALLERY_REQUEST) {
                        Uri imageUri = data.getData();
                        InputStream inputStream;
                        try {
                            inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            ivPicture.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(AddNewPost.this, "Unable to open image", Toast.LENGTH_LONG).show();
                        }
                    } else if (requestCode == IMAGE_CAMERA_REQUEST) {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        ivPicture.setImageBitmap(bitmap);
                    }
                }


            }
        });

    }
}