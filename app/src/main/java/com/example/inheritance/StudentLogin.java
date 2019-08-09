package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Objects;

import static com.example.inheritance.Home.sharedPreferences;

public class StudentLogin extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    Button bGoogleSignin, bLogin, bCancel;
    EditText edtxtEmail, edtxtPwd;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    boolean done;
    private ProgressDialog progressDialog;
    private DatabaseReference dbRef;
    private String program, branch, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        bGoogleSignin =  findViewById(R.id.google_signin_button);
        bLogin =  findViewById(R.id.login_button);
        bCancel =  findViewById(R.id.cancel_login_button);
        edtxtEmail =  findViewById(R.id.email_field);
        edtxtPwd =  findViewById(R.id.password_field);
        TextView tvForgot = findViewById(R.id.tvForgot);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Toast.makeText(StudentLogin.this, "You're logged in!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        if (firebaseAuth.getCurrentUser() != null) {
            Log.w("myTag", "User is already signed in! GO BACK NOW!");
            Intent intent = new Intent(StudentLogin.this, Home.class);
            startActivity(intent);
        }

        bGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("myTag", "Google sign-in opted");
                createGoogleSignInIntent();
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithEmailPwd();
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentLogin.this, PasswordReset.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    private void loginWithEmailPwd() {
        final String email = edtxtEmail.getText().toString().trim();
        final String pwd = edtxtPwd.getText().toString().trim();

        if (!TextUtils.isEmpty(email)) {
            if (!TextUtils.isEmpty(pwd)) {
                //Add login code
                progressDialog.setMessage("Validating, please wait");
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(StudentLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.w("myTag", "Task successful");
                                    Toast.makeText(StudentLogin.this, "Logging in", Toast.LENGTH_SHORT).show();
///********************************************************************************************************************************
                                    dbRef = FirebaseDatabase.getInstance().getReference("users").child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
                                    ValueEventListener valueEventListener = new ValueEventListener() {
                                        @SuppressLint("ApplySharedPref")
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            program = dataSnapshot.child("program").getValue(String.class);
                                            branch = dataSnapshot.child("branch").getValue(String.class);
                                            year = dataSnapshot.child("year").getValue(String.class);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putBoolean("student_logged", true);
                                            editor.putString("program", program);
                                            editor.putString("branch", branch);
                                            editor.putString("year", year);
                                            editor.commit();
                                            done = true;
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    };
                                    dbRef.addValueEventListener(valueEventListener);
// ******************************************************************************************************************************** */

                                    while (firebaseAuth.getCurrentUser() != null && sharedPreferences.getBoolean("student_logged", false)) {

                                    }
                                    progressDialog.dismiss();
                                    Intent backToHome = new Intent(StudentLogin.this, Home.class);
                                    startActivity(backToHome);
                                } else {
                                    if (pwd.length() < 8) {
                                        Log.w("myTag", "Password length less than 8");
                                        Toast.makeText(StudentLogin.this, "Minimum length of password is 8 characters", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.w("myTag", "Invalid email or pwd");
                                        Toast.makeText(StudentLogin.this, "Invalid email address or password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            } else {
                Log.w("myTag", "Password field empty");
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.w("myTag", "Email field empty");
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
        }

    }

    public void createGoogleSignInIntent() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Collections.singletonList(
                                new AuthUI.IdpConfig.GoogleBuilder().build()
//                                            new AuthUI.IdpConfig.EmailBuilder().build() //Requires Dynamic Links, (confirmation email)
                        ))
                        .build(),
                RC_SIGN_IN);
    }
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
//                Successfully signed in
                Log.w("myTag", "Google sign-in successful");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show();
            } else {
//                Google sign-in failed
                Log.w("myTag", "Google sign-in failed");
                Toast.makeText(this, "Sign-in failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
