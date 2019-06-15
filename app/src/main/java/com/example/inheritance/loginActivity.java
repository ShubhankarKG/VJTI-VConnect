package com.example.inheritance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.inheritance.R.layout.content_login;

public class loginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(content_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        final EditText ed1 = (EditText) findViewById(R.id.editText1);
        final EditText ed2 = (EditText) findViewById(R.id.editText2);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("admin@AeroVjti") && ed2.getText().toString().equals("AeroVjti2k19") ||
                        ed1.getText().toString().equals("admin@COC") && ed2.getText().toString().equals("COC2k19") ||
                        ed1.getText().toString().equals("admin@DLA") && ed2.getText().toString().equals("DLA2k19") ||
                        ed1.getText().toString().equals("admin@ECell") && ed2.getText().toString().equals("ECell2k19") ||
                        ed1.getText().toString().equals("admin@Enthusia") && ed2.getText().toString().equals("Enthusia2k19") ||
                        ed1.getText().toString().equals("admin@IEEE") && ed2.getText().toString().equals("IEEE2k19") ||
                        ed1.getText().toString().equals("admin@Pratibimb") && ed2.getText().toString().equals("Pratibimb2k19") ||
                        ed1.getText().toString().equals("admin@Rangavardhan") && ed2.getText().toString().equals("Rangavardhan2k19") ||
                        ed1.getText().toString().equals("admin@SRA") && ed2.getText().toString().equals("SRA2k19") ||
                        ed1.getText().toString().equals("admin@SwachhVjti") && ed2.getText().toString().equals("SwachhVjti2k19") ||
                        ed1.getText().toString().equals("admin@Technovanza") && ed2.getText().toString().equals("Technovanza2k19") ||
                        ed1.getText().toString().equals("admin@VjtiAlumni") && ed2.getText().toString().equals("VjtiAlumni2k19") ||
                        ed1.getText().toString().equals("admin@VjtiRacing") && ed2.getText().toString().equals("VjtiRacing2k19")
                ) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();


                }


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



}
