package com.example.inheritance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ComplexColorCompat;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.view.View.GONE;
import static com.example.inheritance.Home.sharedPreferences;

public class StudentSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int RC_SIGN_IN = 123;
    EditText edtxtEmail, edtxtPwd;
    Button bSignup, bCancel, bGoogleSignup;
    RadioGroup rgProgram;
    TextView txtBranch;
    Spinner yearSpinner, branchSpinner;
    ArrayAdapter<String> yearAdapter, branchAdapter;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private String program, year, branch;
    private String[] BTechBranchList = {"Civil Engineering", "Computer Engineering", "Electrical Engineering",
            "Electronics Engineering", "Electronics and Telecommunication Engineering",
            "Information Technology Engineering", "Mechanical Engineering",
            "Production Engineering", "Textile Engineering"};
    private String[] diplomaBranchList = {"Civil Engineering", "Electrical Engineering", "Electronics Engineering", "Mechanical Engineering",
            "Textile Manufactures", "Chemical Engineering"};

    private String[] MTechBranchList = {"Civil Engineering (Construction Management)", "Civil Engineering (Environmental Engineering)",
            "Civil Engineering (Structural Engineering)", "Computer Engineering", "Computer Engineering (Network Infrastructure Management Systems)",
            "Computer Engineering (Software Engineering)", "Electrical Engineering (Power Systems)",
            "Electrical Engineering (Control Systems)", "Electronics Engineering", "Electronics & Telecommunication Engineering",
            "Mechanical Engineering (Machine Design)", "Mechanical Engineering (Automobile Engineering)",
            "Mechanical Engineering (CAD/CAM & Automation)", "Mechanical Engineering (Thermal Engineering)",
            "Production Engineering", "Project Management", "Textile Technology"};

    private String[] BTechYearList = {"First Year", "Second Year", "Third Year", "Final Year"};
    private String[] diplomaYearList = {"First Year", "Second Year"};
    private String[] mastersYearList = {"First Year", "Second Year"};



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
        rgProgram = (RadioGroup) findViewById(R.id.rg_program);
        branchSpinner = (Spinner) findViewById(R.id.branch_spinner);
        yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        txtBranch = (TextView) findViewById(R.id.txt_branch);

//        branchSpinner.setOnItemClickListener();

        rgProgram.clearCheck();
        rgProgram.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
                switch (radioButton.getText().toString()) {
                    case "BTech":
                        program = "BTech";
                        Log.w("myTag", "program set to BTech");
                        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, BTechYearList);
                        yearSpinner.setAdapter(yearAdapter);
                        yearSpinner.setVisibility(View.VISIBLE);
                        Log.w("myTag", "yearSpinner set");
                        ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, BTechBranchList);
                        branchSpinner.setAdapter(branchAdapter);
                        branchSpinner.setVisibility(View.VISIBLE);
                        txtBranch.setVisibility(View.VISIBLE);
                        Log.w("myTag", "branchSpinner set");
                        break;
                    case "Diploma":
                        program = "Diploma";
                        Log.w("myTag", "program set to Diploma");
                        yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, diplomaYearList);
                        yearSpinner.setAdapter(yearAdapter);
                        yearSpinner.setVisibility(View.VISIBLE);
                        Log.w("myTag", "yearSpinner set");
                        branchAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, diplomaBranchList);
                        branchSpinner.setAdapter(branchAdapter);
                        branchSpinner.setVisibility(View.VISIBLE);
                        txtBranch.setVisibility(View.VISIBLE);
                        Log.w("myTag", "branchSpinner set");
                        break;
                    case "MTech":
                        program = "MTech";
                        Log.w("myTag", "program set to MTech");
                        yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mastersYearList);
                        yearSpinner.setAdapter(yearAdapter);
                        yearSpinner.setVisibility(View.VISIBLE);
                        Log.w("myTag", "yearSpinner set");
                        branchAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, MTechBranchList);
                        branchSpinner.setAdapter(branchAdapter);
                        branchSpinner.setVisibility(View.VISIBLE);
                        txtBranch.setVisibility(View.VISIBLE);
                        Log.w("myTag", "branchSpinner set");
                        break;
                    case "MCA":
                        program = "MCA";
                        Log.w("myTag", "program set to MCA");
                        yearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mastersYearList);
                        yearSpinner.setAdapter(yearAdapter);
                        yearSpinner.setVisibility(View.VISIBLE);
                        Log.w("myTag", "yearSpinner set");
                        branchSpinner.setVisibility(GONE);
                        txtBranch.setVisibility(GONE);
                        Log.w("myTag", "branchSpinner visibility set to GONE");
                        break;
                    default:
                        break;
                }


            }
        });

        yearSpinner.setOnItemSelectedListener(this);
        branchSpinner.setOnItemSelectedListener(this);

        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            synchronized public void onClick(View view) {


                int selectedId = rgProgram.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Log.w("myTag", "No program selected:");
                    Toast.makeText(StudentSignUp.this, "Please select a program to continue", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!program.equals("MCA") && branch == null) {
                        Log.w("myTag", "Branch not selected");
                        Toast.makeText(StudentSignUp.this, "Please enter your branch", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (year == null) {
                        Log.w("myTag", "Year not selected");
                        Toast.makeText(StudentSignUp.this, "Please enter your year", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
//                Add conditions for no options selected in spinners
                try {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("program", program);
                    editor.putString("year", year);
                    if (!program.equals("MCA")) editor.putString("branch", branch);
                    else editor.putString("branch", null);
                    editor.commit();
                    Log.w("myTag", "userCred (sharedPreferences) set");
                    startSignupFirebaseAuth();
                } catch (Exception e) {
                    Log.w("myTag", e.toString());
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
                Log.w("myTag", "Google sign-up opted");
                createGoogleSignUpIntent();
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Spinner spinner = (Spinner) adapterView;
        if (spinner.getId() == R.id.year_spinner) {
            Log.w("myTag", "Year spinner item selected");
            year = spinner.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.branch_spinner) {
            Log.w("myTag", "Branch spinner item selected");
            branch = spinner.getSelectedItem().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        if (((Spinner) adapterView).getId() == R.id.year_spinner) {
            year = null;
        } else if (((Spinner) adapterView).getId() == R.id.branch_spinner) {
            branch = null;
        }
    }

    public void createGoogleSignUpIntent() {
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
                Log.w("myTag", "Google sign up successful");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show();
            } else {
                // Sign in failed
                Log.w("myTag", "Google sign up failed");
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
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("student_logged", true);
                                    editor.commit();
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
