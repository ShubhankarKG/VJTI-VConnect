package com.example.inheritance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPost extends AppCompatActivity {

    EditText inputDate;
    EditText inputTitle;
    EditText inputDescription;
    String Date;
    String Title;
    String Description;
    Button bPicture;
    Button bCamera;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int IMAGE_CAMERA_REQUEST = 30;
    ImageView ivPicture;
    InputStream inputStream;
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
        ivPicture = (ImageView) findViewById(R.id.ivPicture);

        Button btnCreateProduct = findViewById(R.id.btnCreatePost);
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date = inputDate.getText().toString();
                Title = inputTitle.getText().toString();
                Description = inputDescription.getText().toString();


                if (!TextUtils.isEmpty(Title)) {
                    String id = dbRef.push().getKey();
                    Post post = new Post(Title, Description, "");
                    if (id != null) {
                        dbRef.child(id).setValue(post);
                        Toast.makeText(AddPost.this, "Post added successfully", Toast.LENGTH_SHORT).show(); //Toast doesn't come!
                    } else {
                        Toast.makeText(AddPost.this, "Please add a title", Toast.LENGTH_SHORT).show(); //Toast doesn't come!
                    }
                }

            }

        });

        bPicture = (Button) findViewById(R.id.bPicture);
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

        Button bCamera = (Button) findViewById(R.id.bCamera);
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
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                Uri imageUri = data.getData();
                new LoadImageDataTask(imageUri).execute();

/*
try {
inputStream = getContentResolver().openInputStream(imageUri);
Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
ivPicture.setImageBitmap(bitmap);
} catch (FileNotFoundException e) {
e.printStackTrace();
Toast.makeText(AddPost.this, "Unable to open image", Toast.LENGTH_LONG).show();
}
*/
            } else if (requestCode == IMAGE_CAMERA_REQUEST) {

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ivPicture.setImageBitmap(bitmap);
            }
        }
    }


    private class LoadImageDataTask extends AsyncTask<Void, Void, Bitmap>{
        private Uri imagePath;

        LoadImageDataTask(Uri imagePath){
            this.imagePath = imagePath;
        }

        @Override
        protected Bitmap doInBackground(Void... params){
            try{
                InputStream inputStream = getContentResolver().openInputStream(imagePath);
                return BitmapFactory.decodeStream(inputStream);
            }catch (FileNotFoundException e){
                Toast.makeText(AddPost.this, "The file" + imagePath + " does not exist", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            ivPicture.setImageBitmap(bitmap);
            Toast.makeText(AddPost.this, "I got the image data with size, "+ Formatter.formatFileSize(AddPost.this, bitmap.getByteCount()), Toast.LENGTH_LONG).show();
        }

    }


}





