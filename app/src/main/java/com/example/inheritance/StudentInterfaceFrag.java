package com.example.inheritance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.inheritance.Home.sharedPreferences;

public class StudentInterfaceFrag extends Fragment {
    public RecyclerView mNoticesList;
    View view;
    Button bLogin, bLogout;
    RelativeLayout notLoggedLayout, loggedLayout;
    TextView txtClassDetails;
    private List<Post> noticesData;
    private DatabaseReference dbRef;
    private Adapter adapter;
    private ProgressBar progressCircle;
    private String program, branch, year;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_interface, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notLoggedLayout = view.findViewById(R.id.not_logged_msg);
        loggedLayout = view.findViewById(R.id.notices_feed);
        txtClassDetails = view.findViewById(R.id.class_details);
        sharedPreferences = getActivity().getSharedPreferences("userCred", Context.MODE_PRIVATE);
        mNoticesList = view.findViewById(R.id.feed_recyclerview);
        mNoticesList.setHasFixedSize(true);
        mNoticesList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        If user is logged in
        if (sharedPreferences.getBoolean("student_logged", false)) {
            notLoggedLayout.setVisibility(View.GONE);
            loggedLayout.setVisibility(View.VISIBLE);
            progressCircle = view.findViewById(R.id.progress_circle);
            bLogin = (Button) view.findViewById(R.id.login_button);
            bLogout = (Button) view.findViewById(R.id.logout_button);

            noticesData = new ArrayList<>();
            program = sharedPreferences.getString("program", null);
            year = sharedPreferences.getString("year", null);

//            Special case for MCA -_-
            if (!program.equals("MCA")) {
                branch = sharedPreferences.getString("year", null);
                txtClassDetails.setText(program + '/' + branch + '/' + year);
                dbRef = FirebaseDatabase.getInstance().getReference().child("program").child("branch").child("year");
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
                dbRef = FirebaseDatabase.getInstance().getReference().child("program").child("year");
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


            FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);
            if (sharedPreferences.getBoolean("cr_logged", false)) {
                fabAdd.show();  //show FAB for adding post to CR
                bLogin.setVisibility(View.GONE);
                bLogout.setVisibility(View.VISIBLE);
            } else {
                fabAdd.hide();
                bLogout.setVisibility(View.VISIBLE);
                bLogout.setVisibility(View.GONE);
            }

            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    startActivity(intent);
                }
            });

            bLogout.setOnClickListener(new View.OnClickListener() {
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
            notLoggedLayout.setVisibility(View.VISIBLE);
            loggedLayout.setVisibility(View.GONE);
            progressCircle.setVisibility(View.GONE);
            txtClassDetails.setText("");

        }

    }
}
