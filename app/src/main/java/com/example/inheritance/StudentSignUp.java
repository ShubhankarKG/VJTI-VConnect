package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class StudentSignUp extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    EditText edtxtEmail, edtxtPwd;
    Button bSignup, bCancel, bGoogleSignup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        edtxtEmail = (EditText) findViewById(R.id.email_field);
        edtxtPwd = (EditText) findViewById(R.id.password_field);
        bSignup = (Button) findViewById(R.id.sign_up_button);
        bCancel = (Button) findViewById(R.id.cancel_signup_button);
        bGoogleSignup = (Button) findViewById(R.id.google_signup_button);

        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignupFirebaseAuth();
            }
        });

        bGoogleSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSignInIntent();
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show();
            } else {
                // Sign in failed
                Toast.makeText(this, "Sign-in failed!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void startSignupFirebaseAuth() {
        final String email = edtxtEmail.getText().toString().trim();
        final String pwd = edtxtPwd.getText().toString().trim();
        if (!TextUtils.isEmpty(email)) {
            if (pwd.length() > 8) {
                progressDialog.setMessage("Signing up, please wait");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    // Make database entry for new user
                                    HashMap<String, Object> map = new HashMap<String, Object>();
                                    final FirebaseUser user = task.getResult().getUser();
                                    map.put("user_id", user.getUid());
                                    map.put("email", email);
                                    map.put("last_connection", Calendar.getInstance(Locale.US).getTimeInMillis());
                                    DatabaseReference userDbRef = FirebaseDatabase.getInstance()
                                            .getReference().child("users")
                                            .child(user.getUid());
                                    userDbRef.setValue(map)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    //Update username
                                                }
                                            });
                                } else {
                                    // Delete user, because somethibg went wrong
                                    // user.delete();
//                                    Above line doesn't work
                                    Toast.makeText(StudentSignUp.this, "Could not add user", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }

}
