package com.ellah.ellahveehotels.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.HotelViewHolder> {

   private List<Business> mHotels;
   private Context mContext;

    public HotelListAdapter(Context context, List<Business> hotels) {
        mHotels = hotels;
        mContext = context;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotelImageView)
        ImageView mImageLabel;
        @BindView(R.id.hotelNameTextView)
        TextView mNameLabel;
        @BindView(R.id.ratingTextView) TextView mRatingLabel;
        @BindView(R.id.categoryTextView) TextView mCategoriesLabel;

        private Context mContext;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindHotel(Business hotel) {
            mNameLabel.setText(hotel.getName());
            mRatingLabel.setText(String.valueOf(hotel.getRating()));
            mCategoriesLabel.setText(hotel.getCategories().toString());
            Picasso.get().load(hotel.getImageUrl()).into(mImageLabel);
        }
    }

    @NonNull
    @Override
    public HotelListAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.HotelViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
