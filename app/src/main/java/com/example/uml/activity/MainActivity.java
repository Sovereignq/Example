package com.example.uml.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.example.uml.R;
import com.example.uml.activity.core.BaseActivity;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;
import com.example.uml.mvp.manager.MainActivityManagerUI;
import com.example.uml.mvp.manager.core.core.ManagerUI;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import static android.content.ContentValues.TAG;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @AfterViews
    public void init() {
        isStoragePermissionGranted();
        changeFragmentTo(new FragmentData(FragmentById.LOGIN_FRAGMENT));
    }
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    public  boolean isInternetPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    /*public boolean checkSelfPermission(String writeExternalStorage) {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }*/

    @Override
    protected ManagerUI getManagerUIToInit() {
        return new MainActivityManagerUI(this);
    }
}
