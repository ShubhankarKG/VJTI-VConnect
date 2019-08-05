package com.example.inheritance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.Objects;

import static com.example.inheritance.Home.sharedPreferences;

public class AccountSettingFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RC_SIGN_IN = 123;
    //    private static final int RC_SIGNUP = 555;
    public FirebaseAuth firebaseAuth;
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button bLogin;
    private Button bSignup;
    private Button bLogout;

//    private Signup.OnFragmentInteractionListener mSignupListener;

    public AccountSettingFrag() {
//        Required empty public constructor
    }

    public static AccountSettingFrag newInstance(String param1, String param2) {
        AccountSettingFrag fragment = new AccountSettingFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.w("myTag", "onCreateView() evoked");
        if (firebaseAuth.getCurrentUser() == null) {
//            User isn't signed in
            view = inflater.inflate(R.layout.account_settings_not_logged, container, false);
            bLogin = (Button) view.findViewById(R.id.login_button);
            bSignup = (Button) view.findViewById(R.id.sign_up_button);

            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    changeFragment(new LoginFrag());
                    Intent loginIntent = new Intent(getActivity(), StudentLogin.class);
                    startActivity(loginIntent);
                }
            });

            bSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Fragment transaction/transition to Signup Page here

//                    changeFragment(new Signup());
                    Intent signupIntent = new Intent(getActivity(), StudentSignUp.class);
                    Log.w("myTag", "loginIntent created");
                    ((Home) getActivity()).startActivity(signupIntent);
                    Log.w("myTag", "startActivity(signupIntent) done");

//                    Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
//                    if(currentFrag instanceof AccountSettingFrag) {
//                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                        fragmentTransaction.detach(currentFrag);
//                        fragmentTransaction.attach(currentFrag);
//                        fragmentTransaction.commit();
//                    }
                }
            });
            return view;
        } else {
            view = inflater.inflate(R.layout.account_settings_logged, container, false);
            bLogout = (Button) view.findViewById(R.id.logout_button);
            bLogout.setOnClickListener(new View.OnClickListener() {
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
//                        Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
//                        if (currentFrag instanceof AccountSettingFrag) {
//                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                            fragmentTransaction.detach(currentFrag);
//                            fragmentTransaction.attach(currentFrag);
//                            fragmentTransaction.commit();
//                        }

                        Intent intent = new Intent(getActivity(), Home.class);
                        startActivity(intent);

                    }
                }
            });
            return view;

        }

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
//        Log.w("myTag", "onActivityResult evoked");
//        Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
//        if(currentFrag instanceof AccountSettingFrag) {
//            Log.w("myTag", "currentFrag IS instanceof AccountSettingsFrag");
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.detach(currentFrag);
//            fragmentTransaction.attach(currentFrag);
//            fragmentTransaction.commit();
//            Log.w("myTag", "fragmentTransaction committed");
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        firebaseAuth = FirebaseAuth.getInstance();


        Log.w("myTag", "onCreate() evoked");
        final Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.w("myTag", "onAuthStateChanged called in AccountSettingsFrag");

                if (currentFrag instanceof AccountSettingFrag) {
                    Log.w("myTag", "currentFrag is instanceof AccountSettingsFrag");
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.detach(currentFrag);
                    fragmentTransaction.attach(currentFrag);
                    fragmentTransaction.commit();
                }
            }
        });

//        Maybe no setContentView because onCreateView() takes care of it

//        firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() == null) {
////            User isn't logged in
////            account_settings_not_logged.xml is the layout
//            bLogin = (Button) getView().findViewById(R.id.login_button);
//            bSignup = (Button) getView().findViewById(R.id.sign_up_button);
//
//            bLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                     changeFragment(new LoginFrag());
//                }
//            });
//
//            bSignup.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Fragment transaction/transition to Signup Page here
//
//                    changeFragment(new Signup());
//                }
//            });


//        } else {
////            User is logged in
////            account_settings_logged.xml is the layout
//        }


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w("myTag", "onResume() evoked");

        final Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.w("myTag", "onAuthStateChanged called in AccountSettingsFrag in OnResume()");

                if (currentFrag instanceof AccountSettingFrag) {
                    Log.w("myTag", "currentFrag is instanceof AccountSettingsFrag");
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.detach(currentFrag);
                    fragmentTransaction.attach(currentFrag);
                    fragmentTransaction.commit();
                }
            }
        });

//        Uncomment this, and you'll see a nice infinite loop
//        Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
//        if(currentFrag instanceof AccountSettingFrag) {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.detach(currentFrag);
//            fragmentTransaction.attach(currentFrag);
//            fragmentTransaction.commit();
//        }
    }

}


