package com.hoomanlogic.mealhub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MealHubPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_ITEMS = 3;

    public MealHubPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GroceryGroupListFragment.newInstance();
            case 1:
                return MealListFragment.newInstance();
            default:
                return SimpleFragment.newInstance(position);
        }
    }
}
