package com.example.names.presentation.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.names.presentation.view.AddNameFragment;
import com.example.names.presentation.view.ListNamesFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"Add Name", "List Names"};

    public MainFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new ListNamesFragment();
        }
        return new AddNameFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
