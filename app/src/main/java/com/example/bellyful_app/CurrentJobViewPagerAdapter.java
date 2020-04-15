package com.example.bellyful_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class CurrentJobViewPagerAdapter extends FragmentPagerAdapter{
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

    private static int NUM_PAGES = 3; //Change this if you add/remove tabs
    private Bundle fragmentBundle;
    private FragmentManager mFragmentManger;

    public CurrentJobViewPagerAdapter(@NonNull FragmentManager fm,  Bundle data) {
        super(fm);
        mFragmentManger = fm;
        fragmentBundle = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    public void addFragment(Fragment fragment, String title){
        fragment.setArguments(fragmentBundle);
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
