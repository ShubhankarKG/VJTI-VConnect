package com.example.inheritance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    public static SharedPreferences sharedPreferences;
    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Toast.makeText(this, "Welcome to the VJTI App", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        final BottomNavigationView bView = findViewById(R.id.nav_view);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);
        bView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navigation_committees :
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_student :
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_account :
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null){
                    prevMenuItem.setChecked(false);
                }
                else {
                    bView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected" + position);
                bView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bView.getMenu().getItem(position);           }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

//    public static SharedPreferences sharedPreferences;
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment fragment = null;
//
//            switch (item.getItemId()) {
//                case R.id.navigation_committees:
//                    fragment = new MainActivity();
//                    break;
//                case R.id.navigation_student:
//                    fragment = new StudentInterfaceFrag();
//                    break;
//                case R.id.navigation_account:
//                    fragment = new AccountSettingFrag();
//                    break;
//            }
//            return loadFragment(fragment);
//
//        }
//    };
//

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        //loading the default fragment
//        loadFragment(new MainActivity());
//
//        //getting bottom navigation view and attaching the listener
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);
//    }
//
//    private boolean loadFragment(Fragment fragment) {
//        //switching fragment
//        if (fragment != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fragment)
////                    .addToBackStack(null)
//                    .commit();
//            return true;
//        }
//        return false;
//    }

}
