package com.example.inheritance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class AccountSettingFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RC_SIGN_IN = 123;
    public FirebaseAuth firebaseAuth;
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button bLogin;
    private Button bSignup;

    private Signup.OnFragmentInteractionListener mSignupListener;

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
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
//            User isn't signed in
            view = inflater.inflate(R.layout.account_settings_not_logged, container, false);
            bLogin = (Button) view.findViewById(R.id.login_button);
            bSignup = (Button) view.findViewById(R.id.sign_up_button);

            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    changeFragment(new LoginFrag());
                }
            });

            bSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Fragment transaction/transition to Signup Page here

//                    changeFragment(new Signup());
                    Intent loginIntent = new Intent(getActivity(), StudentSignUp.class);
                    startActivity(loginIntent);
                }
            });
            return view;
        } else {
            view = inflater.inflate(R.layout.account_settings_logged, container, false);
            return view;

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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

}


