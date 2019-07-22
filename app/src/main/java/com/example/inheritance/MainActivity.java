package com.example.inheritance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class MainActivity extends Fragment {
    public static SharedPreferences sharedPreferences;
    private FragmentActivity fragmentActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_committee_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Welcome to the VJTI App", Toast.LENGTH_SHORT).show();
        sharedPreferences = getActivity().getSharedPreferences("userCred", Context.MODE_PRIVATE);
        Button bLogin = (Button) view.findViewById(R.id.bLogin);

        if (sharedPreferences.getBoolean("logged", false)) {
            bLogin.setText("Logout");
            bLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("login_id", null);
                    editor.commit();
                    // Probably add a toast saying "Logging out"
                    Intent refresh = new Intent(getActivity(), MainActivity.class);
                    startActivity(refresh);
                }
            });

        } else {
            bLogin.setText("Login");
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button bAeroVjti = (Button) view.findViewById(R.id.bAeroVjti);
        ImageButton ibAeroVjti = (ImageButton) view.findViewById(R.id.ibAero);
        Button bCoc = (Button) view.findViewById(R.id.bCoc);
        ImageButton ibCoc = (ImageButton) view.findViewById(R.id.ibCoc);
        Button bDla = (Button) view.findViewById(R.id.bDla);
        ImageButton ibDla = (ImageButton) view.findViewById(R.id.ibDla);
        Button bEcell = (Button) view.findViewById(R.id.bEcell);
        ImageButton ibEcell = (ImageButton) view.findViewById(R.id.ibEcell);
        Button bEnthu = (Button) view.findViewById(R.id.bEnthu);
        ImageButton ibEnthu = (ImageButton) view.findViewById(R.id.ibEnthu);
        Button bIeee = (Button) view.findViewById(R.id.bIeee);
        ImageButton ibIeee = (ImageButton) view.findViewById(R.id.ibIeee);
        Button bPrati = (Button) view.findViewById(R.id.bPrati);
        ImageButton ibPrati = (ImageButton) view.findViewById(R.id.ibPrati);
        Button bRanga = (Button) view.findViewById(R.id.bRanga);
        ImageButton ibRanga = (ImageButton) view.findViewById(R.id.ibRanga);
        Button bSra = (Button) view.findViewById(R.id.bSra);
        ImageButton ibSra = (ImageButton) view.findViewById(R.id.ibSra);
        Button bSwachh = (Button) view.findViewById(R.id.bSwachh);
        ImageButton ibSwachh = (ImageButton) view.findViewById(R.id.ibSwachh);
        Button bTechno = (Button) view.findViewById(R.id.bTechno);
        ImageButton ibTechno = (ImageButton) view.findViewById(R.id.ibTechno);
        Button bAlumni = (Button) view.findViewById(R.id.bAlumni);
        ImageButton ibAlumni = (ImageButton) view.findViewById(R.id.ibAlumni);
        Button bRacing = (Button) view.findViewById(R.id.bRacing);
        ImageButton ibRacing = (ImageButton) view.findViewById(R.id.ibRacing);

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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public void onResume() {
//        setContentView(R.layout.content_committee_list);
        FrameLayout container = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
        LayoutInflater.from(getActivity())
                .inflate(R.layout.content_committee_list, container, false);

        sharedPreferences = getActivity().getSharedPreferences("userCred", Context.MODE_PRIVATE);

        Button bLogin = (Button) getView().findViewById(R.id.bLogin);

        if (sharedPreferences.getBoolean("logged", false)) {
            bLogin.setText("Logout");
            bLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("login_id", null);
                    editor.commit();
                    // Probably add a toast saying "Logging out"
                    Intent refresh = new Intent(getActivity(), MainActivity.class);
                    startActivity(refresh);
                }
            });

        } else {
            bLogin.setText("Login");
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), loginActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button bAeroVjti = (Button) getView().findViewById(R.id.bAeroVjti);
        ImageButton ibAeroVjti = (ImageButton) getView().findViewById(R.id.ibAero);
        Button bCoc = (Button) getView().findViewById(R.id.bCoc);
        ImageButton ibCoc = (ImageButton) getView().findViewById(R.id.ibCoc);
        Button bDla = (Button) getView().findViewById(R.id.bDla);
        ImageButton ibDla = (ImageButton) getView().findViewById(R.id.ibDla);
        Button bEcell = (Button) getView().findViewById(R.id.bEcell);
        ImageButton ibEcell = (ImageButton) getView().findViewById(R.id.ibEcell);
        Button bEnthu = (Button) getView().findViewById(R.id.bEnthu);
        ImageButton ibEnthu = (ImageButton) getView().findViewById(R.id.ibEnthu);
        Button bIeee = (Button) getView().findViewById(R.id.bIeee);
        ImageButton ibIeee = (ImageButton) getView().findViewById(R.id.ibIeee);
        Button bPrati = (Button) getView().findViewById(R.id.bPrati);
        ImageButton ibPrati = (ImageButton) getView().findViewById(R.id.ibPrati);
        Button bRanga = (Button) getView().findViewById(R.id.bRanga);
        ImageButton ibRanga = (ImageButton) getView().findViewById(R.id.ibRanga);
        Button bSra = (Button) getView().findViewById(R.id.bSra);
        ImageButton ibSra = (ImageButton) getView().findViewById(R.id.ibSra);
        Button bSwachh = (Button) getView().findViewById(R.id.bSwachh);
        ImageButton ibSwachh = (ImageButton) getView().findViewById(R.id.ibSwachh);
        Button bTechno = (Button) getView().findViewById(R.id.bTechno);
        ImageButton ibTechno = (ImageButton) getView().findViewById(R.id.ibTechno);
        Button bAlumni = (Button) getView().findViewById(R.id.bAlumni);
        ImageButton ibAlumni = (ImageButton) getView().findViewById(R.id.ibAlumni);
        Button bRacing = (Button) getView().findViewById(R.id.bRacing);
        ImageButton ibRacing = (ImageButton) getView().findViewById(R.id.ibRacing);

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

//    @Override
//    public void onRestart() {
//        super.onRestart();
//
//
//    }

}
