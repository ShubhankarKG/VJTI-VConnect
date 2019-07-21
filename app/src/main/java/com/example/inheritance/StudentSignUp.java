package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ComplexColorCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
        progressDialog = new ProgressDialog(this);

        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            synchronized public void onClick(View view) {
                try {
                    startSignupFirebaseAuth();
                } catch (Exception e) {
                    Log.e("myTag", e.toString());
                }


//                if(firebaseAuth.getCurrentUser() != null) {
//                    Log.w("myTag", "signed in");
//                    Toast.makeText(StudentSignUp.this, "Signed in", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                else {
//                    Log.w("myTag", "not signed in");
//                }

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
        Log.w("myTag", "Email ID and pwd getText() done");
        if (!TextUtils.isEmpty(email)) {
            Log.w("myTag", "email isn't empty");
            if (pwd.length() >= 8) {
                Log.w("myTag", "Pwd okay");
                progressDialog.setMessage("Signing up, please wait");
                Log.w("myTag", "processdialog message set");
                progressDialog.show();
                Log.w("myTag", "processDialog show");
                firebaseAuth.createUserWithEmailAndPassword(email, pwd) //creates user and signs in if successful
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            synchronized public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.w("myTag", "onComplete() evoked");
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Log.w("myTag", "Task successful");
                                    // Make database entry for new user
                                    HashMap<String, Object> map = new HashMap<String, Object>();
                                    final FirebaseUser user = task.getResult().getUser();
                                    map.put("user_id", user.getUid());
                                    map.put("email", email);
                                    map.put("last_connection", Calendar.getInstance(Locale.US).getTimeInMillis()); // Check this out
                                    DatabaseReference userDbRef = FirebaseDatabase.getInstance()
                                            .getReference().child("users")
                                            .child(user.getUid());
                                    userDbRef.setValue(map)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Log.w("myTag", "User added to Firebase Realtime Database");
                                                }
                                            });

                                    Toast.makeText(StudentSignUp.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                                    Log.w("myTag", "Signed up");
//                                    finish();
                                    Intent backToHome = new Intent(StudentSignUp.this, Home.class);
                                    startActivity(backToHome);
                                    Log.w("myTag", "Intent backToHome sent");
                                } else {

                                    // Delete user, because something went wrong
                                    // user.delete();
//                                    Above line doesn't work. Something went wrong in handling something else that went wrong... Happy Programming!
                                    Toast.makeText(StudentSignUp.this, "Could not add user", Toast.LENGTH_SHORT).show();
                                    Log.w("myTag", "Task not successful");
                                }
                            }
                        });
            } else {
                Log.w("myTag", "Password too short");
                Toast.makeText(this, "Your password should be at least 8 characters long", Toast.LENGTH_SHORT).show();
                edtxtPwd.setText("");
                edtxtPwd.setHint("Password");
            }
        } else {
            Log.w("myTag", "No email entered");
            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show();
        }
    }

}
