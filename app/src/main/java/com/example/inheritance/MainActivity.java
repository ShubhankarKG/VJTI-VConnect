package com.example.inheritance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_committee_list);
        Toast.makeText(getApplicationContext(), "Welcome to the VJTI App", Toast.LENGTH_SHORT).show();

        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);

        Button bLogin = (Button) findViewById(R.id.bLogin);


        if (sharedPreferences.getBoolean("logged", false)) {
            bLogin.setText("Logout");
            bLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("login_id", null);
                    editor.commit();
                    // Probably add a toast saying "Logging out"
                    Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(refresh);
                }
            });

        } else {
            bLogin.setText("Login");
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button bAeroVjti = (Button) findViewById(R.id.bAeroVjti);
        ImageButton ibAeroVjti = (ImageButton) findViewById(R.id.ibAero);
        Button bCoc = (Button) findViewById(R.id.bCoc);
        ImageButton ibCoc = (ImageButton) findViewById(R.id.ibCoc);
        Button bDla = (Button) findViewById(R.id.bDla);
        ImageButton ibDla = (ImageButton) findViewById(R.id.ibDla);
        Button bEcell = (Button) findViewById(R.id.bEcell);
        ImageButton ibEcell = (ImageButton) findViewById(R.id.ibEcell);
        Button bEnthu = (Button) findViewById(R.id.bEnthu) ;
        ImageButton ibEnthu = (ImageButton) findViewById(R.id.ibEnthu);
        Button bIeee = (Button) findViewById(R.id.bIeee);
        ImageButton ibIeee = (ImageButton) findViewById(R.id.ibIeee);
        Button bPrati = (Button) findViewById(R.id.bPrati) ;
        ImageButton ibPrati = (ImageButton) findViewById(R.id.ibPrati);
        Button bRanga = (Button) findViewById(R.id.bRanga);
        ImageButton ibRanga = (ImageButton) findViewById(R.id.ibRanga);
        Button bSra = (Button) findViewById(R.id.bSra);
        ImageButton ibSra = (ImageButton) findViewById(R.id.ibSra);
        Button bSwachh = (Button) findViewById(R.id.bSwachh);
        ImageButton ibSwachh = (ImageButton) findViewById(R.id.ibSwachh);
        Button bTechno = (Button) findViewById(R.id.bTechno);
        ImageButton ibTechno = (ImageButton) findViewById(R.id.ibTechno);
        Button bAlumni = (Button) findViewById(R.id.bAlumni);
        ImageButton ibAlumni = (ImageButton) findViewById(R.id.ibAlumni);
        Button bRacing = (Button) findViewById(R.id.bRacing);
        ImageButton ibRacing = (ImageButton) findViewById(R.id.ibRacing);

        bAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });



        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });



        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });



        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });



        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });



        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });



        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });


        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });



        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });



        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });



        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });




        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });



        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
            }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
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

    @Override
    public void onRestart() {
        super.onRestart();

        setContentView(R.layout.content_committee_list);

        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);

        Button bLogin = (Button) findViewById(R.id.bLogin);

        if (sharedPreferences.getBoolean("logged", false)) {
            bLogin.setText("Logout");
            bLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("login_id", null);
                    editor.commit();
                    // Probably add a toast saying "Logging out"
                    Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(refresh);
                }
            });

        } else {
            bLogin.setText("Login");
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button bAeroVjti = (Button) findViewById(R.id.bAeroVjti);
        ImageButton ibAeroVjti = (ImageButton) findViewById(R.id.ibAero);
        Button bCoc = (Button) findViewById(R.id.bCoc);
        ImageButton ibCoc = (ImageButton) findViewById(R.id.ibCoc);
        Button bDla = (Button) findViewById(R.id.bDla);
        ImageButton ibDla = (ImageButton) findViewById(R.id.ibDla);
        Button bEcell = (Button) findViewById(R.id.bEcell);
        ImageButton ibEcell = (ImageButton) findViewById(R.id.ibEcell);
        Button bEnthu = (Button) findViewById(R.id.bEnthu);
        ImageButton ibEnthu = (ImageButton) findViewById(R.id.ibEnthu);
        Button bIeee = (Button) findViewById(R.id.bIeee);
        ImageButton ibIeee = (ImageButton) findViewById(R.id.ibIeee);
        Button bPrati = (Button) findViewById(R.id.bPrati);
        ImageButton ibPrati = (ImageButton) findViewById(R.id.ibPrati);
        Button bRanga = (Button) findViewById(R.id.bRanga);
        ImageButton ibRanga = (ImageButton) findViewById(R.id.ibRanga);
        Button bSra = (Button) findViewById(R.id.bSra);
        ImageButton ibSra = (ImageButton) findViewById(R.id.ibSra);
        Button bSwachh = (Button) findViewById(R.id.bSwachh);
        ImageButton ibSwachh = (ImageButton) findViewById(R.id.ibSwachh);
        Button bTechno = (Button) findViewById(R.id.bTechno);
        ImageButton ibTechno = (ImageButton) findViewById(R.id.ibTechno);
        Button bAlumni = (Button) findViewById(R.id.bAlumni);
        ImageButton ibAlumni = (ImageButton) findViewById(R.id.ibAlumni);
        Button bRacing = (Button) findViewById(R.id.bRacing);
        ImageButton ibRacing = (ImageButton) findViewById(R.id.ibRacing);

        bAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });



        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });



        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });



        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });



        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });



        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });



        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });


        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });



        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });



        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });


        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });


        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });


        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
            }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
            }
        });

    }

    }
