package com.example.inheritance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

import static com.example.inheritance.Home.sharedPreferences;

public class MainActivity extends Fragment {

    private Button bAeroVjti, bCoc, bDla, bEcell, bEnthu, bIeee, bPrati, bRanga, bSra, bSwachh, bTechno, bAlumni, bRacing;
    private ImageButton ibAeroVjti, ibCoc, ibDla, ibEcell, ibEnthu, ibIeee, ibPrati, ibRanga, ibSra, ibSwachh, ibTechno, ibAlumni, ibRacing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_committee_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("userCred", Context.MODE_PRIVATE);
        Button bLogin =  view.findViewById(R.id.bLogin);

        if (sharedPreferences.getBoolean("logged", false)) {
            bLogin.setText(R.string.Logout);
            bLogin.setAllCaps(false);
            bLogin.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ApplySharedPref")
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("login_id", null);
                    editor.commit();
                    Toast.makeText(getContext(), "Logged out successfully!", Toast.LENGTH_SHORT).show();
                    Intent refresh = new Intent(getActivity(), Home.class);
                    startActivity(refresh);
                }
            });

        } else {
            bLogin.setText("Admin Login");
            bLogin.setAllCaps(false);
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    startActivity(intent);
                }
            });
        }

        bAeroVjti =  view.findViewById(R.id.bAeroVjti);
        ibAeroVjti =  view.findViewById(R.id.ibAero);

        bCoc =  view.findViewById(R.id.bCoc);
        ibCoc =  view.findViewById(R.id.ibCoc);

        bDla =  view.findViewById(R.id.bDla);
        ibDla =  view.findViewById(R.id.ibDla);

        bEcell =  view.findViewById(R.id.bEcell);
        ibEcell =  view.findViewById(R.id.ibEcell);

        bEnthu =  view.findViewById(R.id.bEnthu);
        ibEnthu =  view.findViewById(R.id.ibEnthu);

        bIeee =  view.findViewById(R.id.bIeee);
        ibIeee =  view.findViewById(R.id.ibIeee);

        bPrati =  view.findViewById(R.id.bPrati);
        ibPrati =  view.findViewById(R.id.ibPrati);

        bRanga =  view.findViewById(R.id.bRanga);
        ibRanga =  view.findViewById(R.id.ibRanga);

        bSra =  view.findViewById(R.id.bSra);
        ibSra =  view.findViewById(R.id.ibSra);

        bSwachh =  view.findViewById(R.id.bSwachh);
        ibSwachh =  view.findViewById(R.id.ibSwachh);

        bTechno =  view.findViewById(R.id.bTechno);
        ibTechno =  view.findViewById(R.id.ibTechno);

        bAlumni =  view.findViewById(R.id.bAlumni);
        ibAlumni =  view.findViewById(R.id.ibAlumni);

        bRacing =  view.findViewById(R.id.bRacing);
        ibRacing =  view.findViewById(R.id.ibRacing);

        bAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });

        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });

        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });

        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });

        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });

        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });

        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });

        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });

        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
            }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        FrameLayout container =  Objects.requireNonNull(getActivity()).findViewById(R.id.fragment_container);
        LayoutInflater.from(getActivity())
                .inflate(R.layout.content_committee_list, container, false);

        sharedPreferences = getActivity().getSharedPreferences("userCred", Context.MODE_PRIVATE);

        Button bLogin = Objects.requireNonNull(getView()).findViewById(R.id.bLogin);

        if (sharedPreferences.getBoolean("logged", false)) {
            bLogin.setText("Admin Logout");
            bLogin.setAllCaps(false);
            bLogin.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ApplySharedPref")
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("login_id", null);
                    editor.commit();
                    Intent refresh = new Intent(getActivity(), Home.class);
                    startActivity(refresh);
                }
            });

        } else {
            bLogin.setText("Admin Login");
            bLogin.setAllCaps(false);
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    startActivity(intent);
                }
            });
        }

        bAeroVjti =  getView().findViewById(R.id.bAeroVjti);
        ibAeroVjti =  getView().findViewById(R.id.ibAero);

        bCoc =  getView().findViewById(R.id.bCoc);
        ibCoc =  getView().findViewById(R.id.ibCoc);

        bDla =  getView().findViewById(R.id.bDla);
        ibDla =  getView().findViewById(R.id.ibDla);

        bEcell =  getView().findViewById(R.id.bEcell);
        ibEcell =  getView().findViewById(R.id.ibEcell);

        bEnthu =  getView().findViewById(R.id.bEnthu);
        ibEnthu =  getView().findViewById(R.id.ibEnthu);

        bIeee =  getView().findViewById(R.id.bIeee);
        ibIeee =  getView().findViewById(R.id.ibIeee);

        bPrati =  getView().findViewById(R.id.bPrati);
        ibPrati =  getView().findViewById(R.id.ibPrati);

        bRanga =  getView().findViewById(R.id.bRanga);
        ibRanga =  getView().findViewById(R.id.ibRanga);

        bSra =  getView().findViewById(R.id.bSra);
        ibSra =  getView().findViewById(R.id.ibSra);

        bSwachh =  getView().findViewById(R.id.bSwachh);
        ibSwachh =  getView().findViewById(R.id.ibSwachh);

        bTechno =  getView().findViewById(R.id.bTechno);
        ibTechno =  getView().findViewById(R.id.ibTechno);

        bAlumni =  getView().findViewById(R.id.bAlumni);
        ibAlumni =  getView().findViewById(R.id.ibAlumni);

        bRacing =  getView().findViewById(R.id.bRacing);
        ibRacing =  getView().findViewById(R.id.ibRacing);

        bAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        ibAeroVjti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "AeroVJTI");
                startActivity(intent);
            }
        });

        bCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        ibCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "COC");
                startActivity(intent);
            }
        });

        bDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        ibDla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "DLA");
                startActivity(intent);
            }
        });

        bEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        ibEcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "ECell");
                startActivity(intent);
            }
        });

        bEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });

        ibEnthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Enthusia");
                startActivity(intent);
            }
        });

        bIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });

        ibIeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "IEEE");
                startActivity(intent);
            }
        });

        bPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });

        ibPrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Pratibimb");
                startActivity(intent);
            }
        });

        bRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });

        ibRanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Rangawardhan");
                startActivity(intent);
            }
        });

        bSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });

        ibSra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "SRA");
                startActivity(intent);
            }
        });

        bSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });

        ibSwachh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Swachh VJTI");
                startActivity(intent);
            }
        });

        bTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });

        ibTechno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "Technovanza");
                startActivity(intent);
            }
        });

        bAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });

        ibAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Alumni");
                startActivity(intent);
            }
        });

        bRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
            }
        });

        ibRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feed.class);
                intent.putExtra("committee", "VJTI Racing");
                startActivity(intent);
            }
        });

        super.onResume();
    }

}
