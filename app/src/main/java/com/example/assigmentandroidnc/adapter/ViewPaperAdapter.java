package com.example.assigmentandroidnc.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assigmentandroidnc.course.fragment.CourseFragment;
import com.example.assigmentandroidnc.course.fragment.RegistrationFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPaperAdapter extends FragmentStatePagerAdapter {
    public ViewPaperAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return  new RegistrationFragment();
            case 1:
                return new CourseFragment();
            default:
                return new RegistrationFragment();

        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
