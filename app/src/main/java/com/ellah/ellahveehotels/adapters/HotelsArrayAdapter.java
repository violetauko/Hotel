package com.ellah.ellahveehotels.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ellah.ellahveehotels.models.HotelSearchResponse;

import retrofit2.Callback;

public class HotelsArrayAdapter extends ArrayAdapter{
        private Context mContext;
        private String[] mHotels;



        public HotelsArrayAdapter(Context mContext, int resource, String[] mHotels) {
            super(mContext, resource);
            this.mContext = mContext;
            this.mHotels = mHotels;
        }

        @Override
        public Object getItem(int position) {
            String hotels = mHotels[position];
            return hotels;
        }


        @Override
        public int getCount() {
            return mHotels.length;
        }
    }
