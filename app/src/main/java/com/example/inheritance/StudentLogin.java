package com.example.inheritance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class StudentLogin extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    Button bGoogleSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        bGoogleSignin = (Button) findViewById(R.id.google_signin_button);
        bGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.GoogleBuilder().build()
//                                            new AuthUI.IdpConfig.EmailBuilder().build() //Requires Dynamic Links, (confirmation email)
                                ))
                                .build(),
                        RC_SIGN_IN);
            }
        });


    }
}
