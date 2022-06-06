package com.ellah.ellahveehotels.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.ui.HotelDetailFragment;

import java.util.List;

public class HotelPagerAdapter extends FragmentPagerAdapter {
    private List<Business> mHotels;

    public HotelPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Business> restaurants) {
        super(fm, behavior);
        mHotels = restaurants;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return HotelDetailFragment.newInstance(mHotels.get(position));
    }

    @Override
    public int getCount() {
        return mHotels.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mHotels.get(position).getName();
    }
}
