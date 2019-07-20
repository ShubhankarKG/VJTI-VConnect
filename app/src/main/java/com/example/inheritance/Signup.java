package com.example.inheritance;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Signup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Signup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RC_SIGN_IN = 123;
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txtEmail;
    private EditText txtPwd;
    private Button bSignUp;
    private Button bCancel;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView txtLogin;

    private OnFragmentInteractionListener mListener;

    public Signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signup.
     */
    // TODO: Rename and change types and number of parameters
    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Maybe no setContentView because onCreateView() takes care of it

        firebaseAuth = FirebaseAuth.getInstance();
        txtEmail = (EditText) getView().findViewById(R.id.email_field);
        txtPwd = (EditText) getView().findViewById(R.id.password_field);

    }

    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
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
                Toast.makeText(this.getContext(), "Sign-in successful", Toast.LENGTH_SHORT).show();
            } else {
                // Sign in failed
                Toast.makeText(this.getContext(), "Sign-in failed!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_login, container, false);
        txtEmail = (EditText) view.findViewById(R.id.email_field);
        txtPwd = (EditText) view.findViewById(R.id.password_field);
        bSignUp = (Button) view.findViewById(R.id.sign_up_button);
        bCancel = (Button) view.findViewById(R.id.cancel_signup_button);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignup();
            }
        });


        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;


    }

    private void startSignup() {
        final String email = txtEmail.getText().toString().trim();
        final String pwd = txtPwd.getText().toString().trim();
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
                                    Toast.makeText(Signup.this.getContext(), "Could not add user", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }

    /*
    public void googleLogin(View view) {
        startActivity(new Intent(ActivityRegister.this, ActivityGoogleLogin.class));
        finish();
    }
    */

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
