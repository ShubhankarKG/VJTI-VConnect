package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.util.Arrays;

import static android.view.View.GONE;
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
    //    RadioGroup rgProgram;
//    TextView txtBranch;
//    Spinner yearSpinner, branchSpinner;
//    ArrayAdapter<String> yearAdapter, branchAdapter;
    private String program, branch, year;
//    private String[] BTechBranchList = {"Civil Engineering", "Computer Engineering", "Electrical Engineering",
//            "Electronics Engineering", "Electronics and Telecommunication Engineering",
//            "Information Technology Engineering", "Mechanical Engineering",
//            "Production Engineering", "Textile Engineering"};
//    private String[] diplomaBranchList = {"Civil Engineering", "Electrical Engineering", "Electronics Engineering", "Mechanical Engineering",
//            "Textile Manufactures", "Chemical Engineering"};
//
//    private String[] MTechBranchList = {"Civil Engineering (Construction Management)", "Civil Engineering (Environmental Engineering)",
//            "Civil Engineering (Structural Engineering)", "Computer Engineering", "Computer Engineering (Network Infrastructure Management Systems)",
//            "Computer Engineering (Software Engineering)", "Electrical Engineering (Power Systems)",
//            "Electrical Engineering (Control Systems)", "Electronics Engineering", "Electronics & Telecommunication Engineering",
//            "Mechanical Engineering (Machine Design)", "Mechanical Engineering (Automobile Engineering)",
//            "Mechanical Engineering (CAD/CAM & Automation)", "Mechanical Engineering (Thermal Engineering)",
//            "Production Engineering", "Project Management", "Textile Technology"};
//
//    private String[] BTechYearList = {"First Year", "Second Year", "Third Year", "Final Year"};
//    private String[] diplomaYearList = {"First Year", "Second Year"};
//    private String[] mastersYearList = {"First Year", "Second Year"};


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

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Toast.makeText(StudentLogin.this, "You're logged in!", Toast.LENGTH_SHORT).show();
                }
            }
        };


//        rgProgram = (RadioGroup) findViewById(R.id.rg_program);
//        branchSpinner = (Spinner) findViewById(R.id.branch_spinner);
//        yearSpinner = (Spinner) findViewById(R.id.year_spinner);
//        txtBranch = (TextView) findViewById(R.id.txt_branch);

        if (firebaseAuth.getCurrentUser() != null) {
            Log.w("myTag", "User is already signed in! GO BACK NOW!");
            Intent intent = new Intent(StudentLogin.this, Home.class);
            startActivity(intent);
        }

//        rgProgram.clearCheck();
//        rgProgram.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
//                switch (radioButton.getText().toString()) {
//                    case "BTech":
//                        program = "BTech";
//                        Log.w("myTag", "program set to BTech");
//                        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, BTechYearList);
//                        yearSpinner.setAdapter(yearAdapter);
//                        yearSpinner.setVisibility(View.VISIBLE);
//                        Log.w("myTag", "yearSpinner set");
//                        ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, BTechBranchList);
//                        branchSpinner.setAdapter(branchAdapter);
//                        branchSpinner.setVisibility(View.VISIBLE);
//                        txtBranch.setVisibility(View.VISIBLE);
//                        Log.w("myTag", "branchSpinner set");
//                        break;
//                    case "Diploma":
//                        program = "Diploma";
//                        Log.w("myTag", "program set to Diploma");
//                        yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, diplomaYearList);
//                        yearSpinner.setAdapter(yearAdapter);
//                        yearSpinner.setVisibility(View.VISIBLE);
//                        Log.w("myTag", "yearSpinner set");
//                        branchAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, diplomaBranchList);
//                        branchSpinner.setAdapter(branchAdapter);
//                        branchSpinner.setVisibility(View.VISIBLE);
//                        txtBranch.setVisibility(View.VISIBLE);
//                        Log.w("myTag", "branchSpinner set");
//                        break;
//                    case "MTech":
//                        program = "MTech";
//                        Log.w("myTag", "program set to MTech");
//                        yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mastersYearList);
//                        yearSpinner.setAdapter(yearAdapter);
//                        yearSpinner.setVisibility(View.VISIBLE);
//                        Log.w("myTag", "yearSpinner set");
//                        branchAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, MTechBranchList);
//                        branchSpinner.setAdapter(branchAdapter);
//                        branchSpinner.setVisibility(View.VISIBLE);
//                        txtBranch.setVisibility(View.VISIBLE);
//                        Log.w("myTag", "branchSpinner set");
//                        break;
//                    case "MCA":
//                        program = "MCA";
//                        Log.w("myTag", "program set to MCA");
//                        yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mastersYearList);
//                        yearSpinner.setAdapter(yearAdapter);
//                        yearSpinner.setVisibility(View.VISIBLE);
//                        Log.w("myTag", "yearSpinner set");
//                        branchSpinner.setVisibility(GONE);
//                        txtBranch.setVisibility(GONE);
//                        Log.w("myTag", "branchSpinner visibility set to GONE");
//                        break;
//                    default:
//                        break;
//                }
//
//
//            }
//        });
//
//        yearSpinner.setOnItemSelectedListener(this);
//        branchSpinner.setOnItemSelectedListener(this);

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
//                int selectedId = rgProgram.getCheckedRadioButtonId();
//                if (selectedId == -1) {
//                    Log.w("myTag", "No program selected:");
//                    Toast.makeText(StudentLogin.this, "Please select a program to continue", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    if (!program.equals("MCA") && branch == null) {
//                        Log.w("myTag", "Branch not selected");
//                        Toast.makeText(StudentLogin.this, "Please enter your branch", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (year == null) {
//                        Log.w("myTag", "Year not selected");
//                        Toast.makeText(StudentLogin.this, "Please enter your year", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("program", program);
//                editor.putString("year", year);
//                if (!program.equals("MCA")) editor.putString("branch", branch);
//                else editor.putString("branch", null);
//                editor.commit();
//                Log.w("myTag", "userCred (sharedPreferences) set");
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

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//        Spinner spinner = (Spinner) adapterView;
//        if (spinner.getId() == R.id.year_spinner) {
//            Log.w("myTag", "Year spinner item selected");
//            year = spinner.getSelectedItem().toString();
//        } else if (spinner.getId() == R.id.branch_spinner) {
//            Log.w("myTag", "Branch spinner item selected");
//            branch = spinner.getSelectedItem().toString();
//        }
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//        if (((Spinner) adapterView).getId() == R.id.year_spinner) {
//            year = null;
//        } else if (((Spinner) adapterView).getId() == R.id.branch_spinner) {
//            branch = null;
//        }
//    }


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
//                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Log.w("myTag", "Task successful");
                                    Toast.makeText(StudentLogin.this, "Logging in", Toast.LENGTH_SHORT).show();
///********************************************************************************************************************************
                                    dbRef = FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid());
                                    ValueEventListener valueEventListener = new ValueEventListener() {
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
//                                    while (!done) {
//                                    } //wait
// ******************************************************************************************************************************** */
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
