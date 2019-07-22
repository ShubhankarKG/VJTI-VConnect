package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Arrays;

public class StudentLogin extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    Button bGoogleSignin, bLogin, bCancel;
    EditText edtxtEmail, edtxtPwd;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        bGoogleSignin = (Button) findViewById(R.id.google_signin_button);
        bLogin = (Button) findViewById(R.id.login_button);
        bCancel = (Button) findViewById(R.id.cancel_login_button);
        edtxtEmail = (EditText) findViewById(R.id.email_field);
        edtxtPwd = (EditText) findViewById(R.id.password_field);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            Log.w("myTag", "User is already signed in! GO BACK NOW!");
            Intent intent = new Intent(StudentLogin.this, AccountSettingFrag.class);
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
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Log.w("myTag", "Task successful");
                                    Toast.makeText(StudentLogin.this, "Logging in", Toast.LENGTH_SHORT).show();
                                    // Although technically the user has already logged in
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
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build()
//                                            new AuthUI.IdpConfig.EmailBuilder().build() //Requires Dynamic Links, (confirmation email)
                        ))
                        .build(),
                RC_SIGN_IN);
    }
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //    @Override
//    Not overriding!!!!
    public void OnActivityResult(int requestCode, int resultCode, Intent data) {
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
                Log.w("myTag", "Google sign-in fialed");
                Toast.makeText(this, "Sign-in failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
