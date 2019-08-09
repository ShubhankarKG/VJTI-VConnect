package com.example.inheritance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.inheritance.Home.sharedPreferences;
import static com.example.inheritance.R.layout.content_login;

public class loginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(content_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button b1 = findViewById(R.id.login_button);
        Button b2 = findViewById(R.id.cancel_login_button);
        final EditText ed1 = findViewById(R.id.username_field);
        final EditText ed2 = findViewById(R.id.password_field);

        b1.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (ed1.getText().toString().equals("admin@AeroVjti") && ed2.getText().toString().equals("AeroVjti2k19") ) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@aero");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "AeroVJTI");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@COC") && ed2.getText().toString().equals("COC2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@coc");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "COC");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@DLA") && ed2.getText().toString().equals("DLA2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@dla");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "DLA");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@ECell") && ed2.getText().toString().equals("ECell2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@ecell");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "ECell");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@Enthusia") && ed2.getText().toString().equals("Enthusia2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@enthu");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "Enthusia");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if ( ed1.getText().toString().equals("admin@IEEE") && ed2.getText().toString().equals("IEEE2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@ieee");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "IEEE");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@Pratibimb") && ed2.getText().toString().equals("Pratibimb2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@prati");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "Pratibimb");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@Rangawardhan") && ed2.getText().toString().equals("Rangawardhan2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@ranga");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "Rangawardhan");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if(  ed1.getText().toString().equals("admin@SRA") && ed2.getText().toString().equals("SRA2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@sra");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "SRA");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@SwachhVjti") && ed2.getText().toString().equals("SwachhVjti2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@swachh");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "Swachh VJTI");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if ( ed1.getText().toString().equals("admin@Technovanza") && ed2.getText().toString().equals("Technovanza2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@techno");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "Technovanza");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@VjtiAlumni") && ed2.getText().toString().equals("VjtiAlumni2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@alumni");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "VJTI Alumni");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

                else if (ed1.getText().toString().equals("admin@VjtiRacing") && ed2.getText().toString().equals("VjtiRacing2k19")) {
                    editor.putBoolean("logged", true);
                    editor.putString("login_id", "admin@racing");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Feed.class);
                    intent.putExtra("committee", "VJTI Racing");
                    startActivity(intent);
                    finish();
                    clear_fields();
                }

//                CR logins:
                else if (ed1.getText().toString().equals("cr@SYBTechIT") && ed2.getText().toString().equals("sybtechit")) {
                    editor.putBoolean("cr_logged", true);
                    editor.commit();
                    Toast.makeText(loginActivity.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Home.class);
                    startActivity(intent);
                    clear_fields();
                } else if (ed1.getText().toString().equals("cr@FYBTechIT") && ed2.getText().toString().equals("fybtechit")) {
                    editor.putBoolean("cr_logged", true);
                    editor.commit();
                    Toast.makeText(loginActivity.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, Home.class);
                    startActivity(intent);
                    clear_fields();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    clear_fields();
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

    private void clear_fields() {
        EditText userField = findViewById(R.id.username_field);
        EditText pwdField = findViewById(R.id.password_field);
        userField.setText("");
        pwdField.setText("");
        userField.setHint("Enter username");
        pwdField.setHint("Enter password");
    }

}
