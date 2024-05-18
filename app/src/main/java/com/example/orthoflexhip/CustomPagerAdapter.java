package com.example.orthoflexhip;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment4(); // Home fragment
            case 1:
                return new BlankFragment(); // Patient fragment
            case 2:
                return new BlankFragment2(); // Appointment fragment
            case 3:
                return new BlankFragment3(); // Video fragment
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4; // Number of tabs
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Home";
        }
        else if (position == 1)
        {
            title = "Patient";
        }
        else if (position == 2)
        {
            title = "Appointment";
        }
        else if (position == 3)
        {
            title = "Video";
        }
     return title;
    }
}
