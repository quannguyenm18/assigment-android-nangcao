package com.example.assigmentandroidnc.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.assigmentandroidnc.MainActivity;
import com.example.assigmentandroidnc.R;
import com.example.assigmentandroidnc.adapter.ViewPaperAdapter;
import com.example.assigmentandroidnc.course.fragment.CourseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class CourseActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        bottomNavigationView=findViewById(R.id.bt_navi);
        viewPager=findViewById(R.id.vp_View);
        Log.d("OnCreate : ", "activity_Coure");

        setUpViewPager();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.registration:
                        Toast.makeText(CourseActivity.this,"Registration",Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(0);
                        break;
                    case  R.id.mysourse:
                        Toast.makeText(CourseActivity.this,"My Course",Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart: ", "activity_course");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroys: ", "activity_course");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("OnPause: ", "activity_course");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("OnStop: ", "activity_course");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart ", "activity_course");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setUpViewPager() {

        ViewPaperAdapter viewPaperAdapter= new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPaperAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.registration).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.mysourse).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }




}