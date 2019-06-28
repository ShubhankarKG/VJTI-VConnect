package com.example.inheritance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
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
                Intent intent = new Intent(MainActivity.this, FeedActivityAero.class);
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityAero.class);
                startActivity(intent);
            }
        });



        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityCoc.class);
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityCoc.class);
                startActivity(intent);
            }
        });



        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityDla.class);
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityDla.class);
                startActivity(intent);
            }
        });



        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityEcell.class);
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityEcell.class);
                startActivity(intent);
            }
        });



        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityEnthusia.class));
            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityEnthusia.class));
            }
        });



        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityIeee.class));
            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityIeee.class));
            }
        });



        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityPratibimb.class));
            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityPratibimb.class));
            }
        });


        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityRangawardhan.class));
            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityRangawardhan.class));
            }
        });



        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySra.class));
            }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySra.class));
            }
        });



        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySwachhVjti.class));
            }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySwachhVjti.class));
            }
        });



        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityTechnovanza.class));
            }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityTechnovanza.class));
            }
        });




        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedAlumni.class));
            }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedAlumni.class));
            }
        });



        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedRacing.class));
            }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedRacing.class));
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
                Intent intent = new Intent(MainActivity.this, FeedActivityAero.class);
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityAero.class);
                startActivity(intent);
            }
        });


        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityCoc.class);
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityCoc.class);
                startActivity(intent);
            }
        });


        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityDla.class);
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityDla.class);
                startActivity(intent);
            }
        });


        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityEcell.class);
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityEcell.class);
                startActivity(intent);
            }
        });


        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityEnthusia.class));
            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityEnthusia.class));
            }
        });


        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityIeee.class));
            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityIeee.class));
            }
        });


        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityPratibimb.class));
            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityPratibimb.class));
            }
        });


        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityRangawardhan.class));
            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityRangawardhan.class));
            }
        });


        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySra.class));
            }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySra.class));
            }
        });


        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySwachhVjti.class));
            }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivitySwachhVjti.class));
            }
        });


        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityTechnovanza.class));
            }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivityTechnovanza.class));
            }
        });


        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedAlumni.class));
            }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedAlumni.class));
            }
        });


        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedRacing.class));
            }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFeedRacing.class));
            }
        });

    }

    }
