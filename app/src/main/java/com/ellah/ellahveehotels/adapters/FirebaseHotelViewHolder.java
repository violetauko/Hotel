package com.ellah.ellahveehotels.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ellah.ellahveehotels.Constants;
import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.ui.BookingActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseHotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebaseHotelViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindHotel(Business hotel){
        ImageView hotelImageView = (ImageView) mView.findViewById(R.id.hotelImageView);
        TextView hotelNameTextView = (TextView) mView.findViewById(R.id.hotelNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        Picasso.get().load(hotel.getImageUrl()).into(hotelImageView);

        hotelNameTextView.setText(hotel.getName());
        ratingTextView.setText("Rating: " + hotel.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Business> hotels = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOTELS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {//addListenerForSingleValueEvent is used to get the data from firebase once and only once.

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    hotels.add(snapshot.getValue(Business.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BookingActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("hotels", Parcels.wrap(hotels));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}