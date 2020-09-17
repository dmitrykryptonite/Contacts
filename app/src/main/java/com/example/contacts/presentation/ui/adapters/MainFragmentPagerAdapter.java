package com.example.contacts.presentation.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.contacts.presentation.view.AddContactFragment;
import com.example.contacts.presentation.view.ListContactsFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"Add Contact", "All Contacts"};

    public MainFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new ListContactsFragment();
        }
        return new AddContactFragment();
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
