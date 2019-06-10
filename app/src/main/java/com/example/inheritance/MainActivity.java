package com.example.inheritance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Welcome to the VJTI App", Toast.LENGTH_SHORT).show();
    }

    Button b1 = (Button) findViewById(R.id.button);

}
