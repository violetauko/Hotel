package com.ellah.ellahveehotels.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.ui.HotelDetailActivity;
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

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();//get the context of the itemView (the activity)

            itemView.setOnClickListener(this);
        }
        public void bindHotel(Business hotel) {
            mNameLabel.setText(hotel.getName());
            mRatingLabel.setText(" Hotel Rating at: "+  hotel.getRating());
            mCategoriesLabel.setText(hotel.getCategories().get(0).getTitle());
            Picasso.get().load(hotel.getImageUrl()).into(mImageLabel);

        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, HotelDetailActivity.class);

            intent.putExtra("position",itemPosition);
            intent.putExtra("hotels", Parcels.wrap(mHotels));
            mContext.startActivity(intent);
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