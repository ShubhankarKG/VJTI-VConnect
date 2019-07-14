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

        Button bLogin = findViewById(R.id.bLogin);


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

        Button bAeroVjti = findViewById(R.id.bAeroVjti);
        ImageButton ibAeroVjti = findViewById(R.id.ibAero);
        Button bCoc = findViewById(R.id.bCoc);
        ImageButton ibCoc = findViewById(R.id.ibCoc);
        Button bDla = findViewById(R.id.bDla);
        ImageButton ibDla = findViewById(R.id.ibDla);
        Button bEcell = findViewById(R.id.bEcell);
        ImageButton ibEcell = findViewById(R.id.ibEcell);
        Button bEnthu = findViewById(R.id.bEnthu);
        ImageButton ibEnthu = findViewById(R.id.ibEnthu);
        Button bIeee = findViewById(R.id.bIeee);
        ImageButton ibIeee = findViewById(R.id.ibIeee);
        Button bPrati = findViewById(R.id.bPrati);
        ImageButton ibPrati = findViewById(R.id.ibPrati);
        Button bRanga = findViewById(R.id.bRanga);
        ImageButton ibRanga = findViewById(R.id.ibRanga);
        Button bSra = findViewById(R.id.bSra);
        ImageButton ibSra = findViewById(R.id.ibSra);
        Button bSwachh = findViewById(R.id.bSwachh);
        ImageButton ibSwachh = findViewById(R.id.ibSwachh);
        Button bTechno = findViewById(R.id.bTechno);
        ImageButton ibTechno = findViewById(R.id.ibTechno);
        Button bAlumni = findViewById(R.id.bAlumni);
        ImageButton ibAlumni = findViewById(R.id.ibAlumni);
        Button bRacing = findViewById(R.id.bRacing);
        ImageButton ibRacing = findViewById(R.id.ibRacing);

        bAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });



        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });



        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivityDla.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });



        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });



        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });



        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });



        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });


        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Ranhawardhan");
                startActivity(intent);
            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });



        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });



        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });



        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });


        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);            }
        });



        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);            }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);            }
        });





    }

    @Override
    public void onRestart() {
        super.onRestart();

        setContentView(R.layout.content_committee_list);

        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);

        Button bLogin = findViewById(R.id.bLogin);

        if (sharedPreferences.getBoolean("logged", false)) {
            bLogin.setText("Logout");
            bLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("login_id", null);
                    editor.commit();
                    Toast.makeText(MainActivity.this,"You have been logged out!", Toast.LENGTH_SHORT).show();
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

        Button bAeroVjti = findViewById(R.id.bAeroVjti);
        ImageButton ibAeroVjti = findViewById(R.id.ibAero);
        Button bCoc = findViewById(R.id.bCoc);
        ImageButton ibCoc = findViewById(R.id.ibCoc);
        Button bDla = findViewById(R.id.bDla);
        ImageButton ibDla = findViewById(R.id.ibDla);
        Button bEcell = findViewById(R.id.bEcell);
        ImageButton ibEcell = findViewById(R.id.ibEcell);
        Button bEnthu = findViewById(R.id.bEnthu);
        ImageButton ibEnthu = findViewById(R.id.ibEnthu);
        Button bIeee = findViewById(R.id.bIeee);
        ImageButton ibIeee = findViewById(R.id.ibIeee);
        Button bPrati = findViewById(R.id.bPrati);
        ImageButton ibPrati = findViewById(R.id.ibPrati);
        Button bRanga = findViewById(R.id.bRanga);
        ImageButton ibRanga = findViewById(R.id.ibRanga);
        Button bSra = findViewById(R.id.bSra);
        ImageButton ibSra = findViewById(R.id.ibSra);
        Button bSwachh = findViewById(R.id.bSwachh);
        ImageButton ibSwachh = findViewById(R.id.ibSwachh);
        Button bTechno = findViewById(R.id.bTechno);
        ImageButton ibTechno = findViewById(R.id.ibTechno);
        Button bAlumni = findViewById(R.id.bAlumni);
        ImageButton ibAlumni = findViewById(R.id.ibAlumni);
        Button bRacing = findViewById(R.id.bRacing);
        ImageButton ibRacing = findViewById(R.id.ibRacing);

        bAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });


        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });


        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });


        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });


        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);            }
        });


        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);            }
        });


        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);            }
        });


        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);              }
        });


        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);              }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);              }
        });


        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);              }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);              }
        });


        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);              }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);              }
        });


        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);              }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);              }
        });


        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);              }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);              }
        });

    }

    }
