package com.example.windows.three;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by windows on 9/14/2017.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private int NUM_ITEMS=2;
    private String[] titles = new String[]{"General","Personal"};
    public TabPagerAdapter (FragmentManager fm){
        super(fm);
    }
    @Override
    public int getCount(){
        return NUM_ITEMS;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new GenAnnouncement();
            case 1:
                return new PerAnnouncement();
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        return titles[position];
    }
}
