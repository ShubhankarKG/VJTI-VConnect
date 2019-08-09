package com.example.inheritance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static com.example.inheritance.Home.sharedPreferences;

public class AccountSettingFrag extends Fragment {

    private FirebaseAuth firebaseAuth;
    View view;

    public AccountSettingFrag() {
//        Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.w("myTag", "onCreateView() evoked");
        if (firebaseAuth.getCurrentUser() == null) {
//            User isn't signed in
            view = inflater.inflate(R.layout.account_settings_not_logged, container, false);
            Button bLogin =  view.findViewById(R.id.login_button);
            Button bSignup =  view.findViewById(R.id.sign_up_button);

            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent loginIntent = new Intent(getActivity(), StudentLogin.class);
                    startActivity(loginIntent);
                }
            });

            bSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signupIntent = new Intent(getActivity(), StudentSignUp.class);
                    Log.w("myTag", "loginIntent created");
                    ( Objects.requireNonNull(getActivity())).startActivity(signupIntent);
                    Log.w("myTag", "startActivity(signupIntent) done");

                }
            });
            return view;
        } else {
            view = inflater.inflate(R.layout.account_settings_logged, container, false);
            Button bLogout =  view.findViewById(R.id.logout_button);
            bLogout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ApplySharedPref")
                @Override
                public void onClick(View view) {
                    if (firebaseAuth.getCurrentUser() != null) {
                        firebaseAuth.signOut();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("program");
                        editor.remove("branch");
                        editor.remove("year");
                        editor.putBoolean("student_logged", false);
                        editor.commit();
                        Log.w("myTag", "User logged out");
                        Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), Home.class);
                        startActivity(intent);

                    }
                }
            });
            return view;

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        Log.w("myTag", "onCreate() evoked");
        assert getFragmentManager() != null;
        final Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.w("myTag", "onAuthStateChanged called in AccountSettingsFrag");

                if (currentFrag instanceof AccountSettingFrag) {
                    Log.w("myTag", "currentFrag is instanceof AccountSettingsFrag");
                    FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                    fragmentTransaction.detach(currentFrag);
                    fragmentTransaction.attach(currentFrag);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w("myTag", "onResume() evoked");

        assert getFragmentManager() != null;
        final Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.w("myTag", "onAuthStateChanged called in AccountSettingsFrag in OnResume()");

                if (currentFrag instanceof AccountSettingFrag) {
                    Log.w("myTag", "currentFrag is instanceof AccountSettingsFrag");
                    FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                    fragmentTransaction.detach(currentFrag);
                    fragmentTransaction.attach(currentFrag);
                    fragmentTransaction.commit();
                }
            }
        });

    }

}


