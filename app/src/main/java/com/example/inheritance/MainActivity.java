package com.example.inheritance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_committee_list);
        Toast.makeText(getApplicationContext(), "Welcome to the VJTI App", Toast.LENGTH_SHORT).show();
        Button bLogin = (Button) findViewById(R.id.login_button);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });

        Button bAeroVjti = (Button) findViewById(R.id.bAeroVjti);
        Button bCoc = (Button) findViewById(R.id.bCoc);
        Button bDla = (Button) findViewById(R.id.bDla);
        Button bEcell = (Button) findViewById(R.id.bEcell);
        Button bEnthu = (Button) findViewById(R.id.bEnthu) ;
        Button bIeee = (Button) findViewById(R.id.bIeee);
        Button bPrati = (Button) findViewById(R.id.bPrati) ;
        Button bRanga = (Button) findViewById(R.id.bRanga);
        Button bSra = (Button) findViewById(R.id.bSra);
        Button bSwachh = (Button) findViewById(R.id.bSwachh);
        Button bTechno = (Button) findViewById(R.id.bTechno);
        Button bAlumni = (Button) findViewById(R.id.bAlumni);
        Button bRacing = (Button) findViewById(R.id.bRacing);

        bAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainAero.class);
                startActivity(intent);
            }
        });

        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainCoc.class);
                startActivity(intent);
            }
        });

        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainDla.class);
                startActivity(intent);
            }
        });

        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainEcell.class);
                startActivity(intent);
            }
        });

        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainEnthu.class));
            }
        });

        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainIeee.class));
            }
        });

        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainPrati.class));
            }
        });

        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainRanga.class));
            }
        });

        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainSra.class));
            }
        });

        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainSwachh.class));
            }
        });

        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainTechno.class));
            }
        });

        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainAlumni.class));
            }
        });

        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainRacing.class));
            }
        });



 /*
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
*/
    }

    }
