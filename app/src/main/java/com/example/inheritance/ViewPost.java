package com.example.inheritance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        TextView vpPostTitle = (TextView) findViewById(R.id.tvviewpost_title);
        TextView vpPostDate = (TextView) findViewById(R.id.tvviewpost_date);
        TextView vpPostDescription = (TextView) findViewById(R.id.tvviewpost_description);
        ImageView vpImage = (ImageView) findViewById(R.id.ivviewpost_image);
  //      vpPostDate.setText(date);


    }
}
