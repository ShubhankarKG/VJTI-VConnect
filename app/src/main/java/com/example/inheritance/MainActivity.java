package com.example.inheritance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inheritance.data.DatabaseHandler;
import com.example.inheritance.data.Feed;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_committee_list);
        Toast.makeText(getApplicationContext(),"Welcome to the VJTI App", Toast.LENGTH_SHORT).show();
        Button b1 = (Button) findViewById(R.id.login_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });

        DatabaseHandler db = new DatabaseHandler(this) ;

        //Inserting Feeds
        Toast.makeText(getApplicationContext(), "Hey..it's working", Toast.LENGTH_SHORT).show();
        db.addFeed(new Feed("Techno1st", "Made to show database works just fine"));
        db.addFeed(new Feed("Enthu1", "This works just as well"));
        db.addFeed(new Feed("22-01-2019", "SRA2k19", "Use of date"));
        db.addFeed(new Feed("15-06-2019" , "COC", "Lasr one."));
        Toast.makeText(getApplicationContext()," Hey.. it worked" , Toast.LENGTH_SHORT).show();
        //Reading all Feeds
        Log.d("Reading", " Reading all feeds...");
        List <Feed> feed = db.getAllFeeds();

        for(Feed fn : feed) {
            String log = "Date:" + fn.getDate() + ", Title: " + fn.getTitle() + ", Description: " + fn.getDescription();

            //Writing Feeds to Log
            Log.d("Name: ", log);
        }
    }

}
