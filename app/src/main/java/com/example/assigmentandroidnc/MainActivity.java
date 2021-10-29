package com.example.assigmentandroidnc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.assigmentandroidnc.course.CourseActivity;
import com.example.assigmentandroidnc.maps.MapsActivity;
import com.example.assigmentandroidnc.news.NewsActivity;
import com.example.assigmentandroidnc.social.SocialActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.assigmentandroidnc",                  //Insert your own package name.
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


    }

    public void Course(View view) {
        Intent intent= new Intent(MainActivity.this, CourseActivity.class);
        startActivity(intent);
    }

    public void News(View view) {
        Intent intent= new Intent(MainActivity.this, NewsActivity.class);
        startActivity(intent);
    }

    public void Maps(View view) {
        Intent intent= new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void social(View view) {
        Intent intent= new Intent(MainActivity.this, SocialActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}