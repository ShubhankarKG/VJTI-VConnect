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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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
    GoogleSignInClient mGoogleSignInClient;
    private ProgressDialog progressDialog;
    private DatabaseReference dbRef;
    private String program, branch, year;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        bGoogleSignin = findViewById(R.id.google_signin_button);
        bLogin = findViewById(R.id.login_button);
        bCancel = findViewById(R.id.cancel_login_button);
        edtxtEmail = findViewById(R.id.email_field);
        edtxtPwd = findViewById(R.id.password_field);
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

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        bGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("myTag", "Google sign-in opted");
                googleSignIn();
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(authStateListener);
//    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
                            synchronized public void onComplete(@NonNull Task<AuthResult> task) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("myTag", "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("myTag", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        progressDialog.setMessage("Signing in with Google");
        progressDialog.show();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    synchronized public void onComplete(@NonNull Task<AuthResult> task) {   // Sometimes StudentInterfaceFrag shows current user's details instantly
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("myTag", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            dbRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
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
                            while (firebaseAuth.getCurrentUser() != null && sharedPreferences.getBoolean("student_logged", false)) {

                            }
                            progressDialog.dismiss();

//                            finish(); // Back button then won't get the user back to login, but home not getting refreshed, so sharedprefs not accessed
//                            Hence general program/branch/year being seen...
//                            So this intent is added
                            Intent backToHome = new Intent(StudentLogin.this, Home.class);
                            startActivity(backToHome);
//                            Trying to add finish() here
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("myTag", "signInWithCredential:failure", task.getException());
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(StudentLogin.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

