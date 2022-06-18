package com.ellah.ellahveehotels.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ellah.ellahveehotels.Constants;
import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.ui.HotelDetailActivity;
import com.ellah.ellahveehotels.ui.HotelDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcel;
import org.parceler.Parcels;

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

    public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.hotelImageView)
        ImageView mImageLabel;
        @BindView(R.id.hotelNameTextView)
        TextView mNameLabel;
        @BindView(R.id.ratingTextView) TextView mRatingLabel;
        @BindView(R.id.categoryTextView) TextView mCategoriesLabel;

        private Context mContext;
        private int mOrientation;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();//get the context of the itemView (the activity)

            // Determines the current orientation of the device:
            mOrientation = itemView.getResources().getConfiguration().orientation;

            // Checks if the recorded orientation matches Android's landscape configuration.
            // if so, we create a new DetailFragment to display in our special landscape layout:
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }
        private void createDetailFragment(int position) {
            HotelDetailFragment detailFragment = HotelDetailFragment.newInstance(mHotels, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.hotelDetailContainer, detailFragment);
            // Commits these changes:
            ft.commit();
            // Creates a new FragmentTransaction:

        }
        @Override
        public void onClick(View view) {
            //detemines the position of the clicked hotel in the list:
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, HotelDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_HOTELS, Parcels.wrap(mHotels));
                mContext.startActivity(intent);
            }
        }

        public void bindHotel(Business hotel) {
            mNameLabel.setText(hotel.getName());
            mRatingLabel.setText(" Hotel Rating at: "+  hotel.getRating());
            mCategoriesLabel.setText(hotel.getCategories().get(0).getTitle());
            Picasso.get().load(hotel.getImageUrl()).into(mImageLabel);

        }
    }

    @NonNull
    @Override
    public HotelListAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
        HotelViewHolder viewHolder = new HotelViewHolder(view);//viewHolder is the object that will be returned
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.HotelViewHolder holder, int position) {
        Business hotel = mHotels.get(position);//get the hotel at the position
        holder.bindHotel(hotel);//bind the hotel to the viewholder

    }

    @Override
    public int getItemCount() {
        return mHotels.size();//returns the size of the list
    }
}
