package com.example.inheritance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    // Variable declaration
    public static SharedPreferences sharedPreferences;
    private ViewPagerAdapter viewPagerAdapter;
    BottomNavigationView bView;
    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Toast.makeText(this, "Welcome to the VJTI App", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        FirebaseApp.initializeApp(this);
        final ViewPager viewPager = findViewById(R.id.viewpager);
        bView = findViewById(R.id.nav_view);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        sharedPreferences = getSharedPreferences("userCred", Context.MODE_PRIVATE);

        bView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navigation_committees:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_student:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_account:
                    viewPager.setCurrentItem(2);
                    break;
                }
                return false;
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
    protected void onResume() {
        super.onResume();
        if (viewPagerAdapter != null) {
            viewPagerAdapter.notifyDataSetChanged();
        }
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
                        finishAffinity();
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

    @Override
    protected void onRestart() {
        super.onRestart();

    }

}
