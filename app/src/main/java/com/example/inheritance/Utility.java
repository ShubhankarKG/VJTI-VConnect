package com.example.inheritance;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Utility {
    public static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context){
        int currentApiVersion = Build.VERSION.SDK_INT;
        if(currentApiVersion>= android.os.Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, Manifest.permission.READ_EXTERNAL_STORAGE)){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permissions necessary");
                    alertBuilder.setMessage("External storage permission is neccesary");
                    alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialogInterface, int i) {
                           ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog dialog = alertBuilder.create();
                    dialog.show();
                }else {
                    ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                }
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }


}
