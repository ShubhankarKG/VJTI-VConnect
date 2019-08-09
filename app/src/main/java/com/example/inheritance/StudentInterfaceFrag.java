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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.inheritance.Home.sharedPreferences;

public class StudentInterfaceFrag extends Fragment {
    private RecyclerView mNoticesList;
    View view;
    private List<Post> noticesData;
    private Adapter adapter;
    private ProgressBar progressCircle;
    private String program, branch, year;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.w("myTag", "onCreateView() of Student Interface Frag called!");
        view = inflater.inflate(R.layout.fragment_student_interface, container, false);
        RelativeLayout notLoggedLayout = view.findViewById(R.id.not_logged_msg);
        RelativeLayout loggedLayout = view.findViewById(R.id.notices_feed);
        TextView txtClassDetails = view.findViewById(R.id.class_details);
        mNoticesList = view.findViewById(R.id.feed_recyclerview);
        mNoticesList.setHasFixedSize(true);
        mNoticesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressCircle = view.findViewById(R.id.progress_circle);
        Button bLogin =  view.findViewById(R.id.login_button);
        Button bLogout =  view.findViewById(R.id.logout_button);
        FloatingActionButton fabAdd =  view.findViewById(R.id.fabAdd);

        if (firebaseAuth.getCurrentUser() != null) {
            Log.w("myTag", "inside OnCreateView() of StudentInterface, firebase user != null");
            notLoggedLayout.setVisibility(View.GONE);
            loggedLayout.setVisibility(View.VISIBLE);
            noticesData = new ArrayList<>();
            program = sharedPreferences.getString("program", null);
            year = sharedPreferences.getString("year", null);

            if(program!=null) {
                DatabaseReference dbRef;
                if (!program.equals("MCA")) {
                    branch = sharedPreferences.getString("branch", null);
                    txtClassDetails.setText(program + '/' + branch + '/' + year);
                    dbRef = FirebaseDatabase.getInstance().getReference(program).child(branch).child(year);
                    dbRef.keepSynced(true);
                    dbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            noticesData.clear();
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Post post1 = dataSnapshot1.getValue(Post.class);
                                    noticesData.add(post1);
                                }
                                adapter = new Adapter(getActivity(), noticesData, program, branch, year);
                                mNoticesList.setAdapter(adapter);

                            }
                            progressCircle.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            progressCircle.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    txtClassDetails.setText(program + '/' + '/' + year);
                    dbRef = FirebaseDatabase.getInstance().getReference(program).child(year);
                    dbRef.keepSynced(true);
                    dbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            noticesData.clear();
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Post post1 = dataSnapshot1.getValue(Post.class);
                                    noticesData.add(post1);
                                }
                                adapter = new Adapter(getActivity(), noticesData, program, branch, year);
                                mNoticesList.setAdapter(adapter);

                            }
                            progressCircle.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            progressCircle.setVisibility(View.INVISIBLE);
                        }
                    });
                }

            }
            if (sharedPreferences.getBoolean("cr_logged", false)) {
                fabAdd.show();  //show FAB for adding post to CR
                bLogin.setVisibility(View.GONE);
                bLogout.setVisibility(View.VISIBLE);
            } else {
                fabAdd.hide();
                bLogout.setVisibility(View.GONE);
                bLogin.setVisibility(View.VISIBLE);
            }

            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    startActivity(intent);
                }
            });

            bLogout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ApplySharedPref")
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("cr_logged", false);
                    editor.commit();
                    Intent refresh = new Intent(getActivity(), Home.class);
                    startActivity(refresh);
                }
            });


            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Adding a new post", Toast.LENGTH_SHORT).show();
                    Intent addNotice = new Intent(getActivity(), AddPost.class);
                    addNotice.putExtra("purpose", "notice");
                    addNotice.putExtra("program", program);
                    addNotice.putExtra("branch", branch);
                    addNotice.putExtra("year", year);
                    startActivity(addNotice);
                }
            });


        } else {
            Log.w("myTag", "inside onCreateView() of Student Interface frag, student_logged == false");
            notLoggedLayout.setVisibility(View.VISIBLE);
            loggedLayout.setVisibility(View.GONE);
            progressCircle.setVisibility(View.GONE);
            txtClassDetails.setVisibility(View.GONE);
            bLogout.setVisibility(View.GONE);
            bLogin.setVisibility(View.GONE);
        }


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        assert getFragmentManager() != null;
        final Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.w("myTag", "onAuthStateChanged called in StudentInterfaceFrag");

                if (currentFrag instanceof AccountSettingFrag) {
                    Log.w("myTag", "currentFrag is instanceof StudentInterfaceFrag");
                    FragmentTransaction fragmentTransaction = null;
                    if (getFragmentManager() != null) {
                        fragmentTransaction = getFragmentManager().beginTransaction();
                    }
                    if (fragmentTransaction != null) {
                        fragmentTransaction.detach(currentFrag);
                    }
                    if (fragmentTransaction != null) {
                        fragmentTransaction.attach(currentFrag);
                    }
                    if (fragmentTransaction != null) {
                        fragmentTransaction.commit();
                    }
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        assert getFragmentManager() != null;
        final Fragment currentFrag = getFragmentManager().findFragmentById(R.id.fragment_container);
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.w("myTag", "onAuthStateChanged called in StudentInterfaceFrag in onResume()");

                if (currentFrag instanceof AccountSettingFrag) {
                    Log.w("myTag", "currentFrag is instanceof StudentInterfaceFrag in onResume()");
                    FragmentTransaction fragmentTransaction = null;
                    if (getFragmentManager() != null) {
                        fragmentTransaction = getFragmentManager().beginTransaction();
                    }
                    if (fragmentTransaction != null) {
                        fragmentTransaction.detach(currentFrag);
                    }
                    if (fragmentTransaction != null) {
                        fragmentTransaction.attach(currentFrag);
                    }
                    if (fragmentTransaction != null) {
                        fragmentTransaction.commit();
                    }
                }
            }
        });
    }
}