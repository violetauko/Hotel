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
import com.ellah.ellahveehotels.ui.HotelListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseHotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView mHotelImageView;
    View mView;
    Context mContext;

    public FirebaseHotelViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindHotel(Business hotel){
         mHotelImageView = (ImageView) mView.findViewById(R.id.hotelImageView);
        TextView hotelNameTextView = (TextView) mView.findViewById(R.id.hotelNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        Picasso.get().load(hotel.getImageUrl()).into(mHotelImageView);

        hotelNameTextView.setText(hotel.getName());
        ratingTextView.setText("Rating: " + hotel.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Business> hotels = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOTELS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {//addListenerForSingleValueEvent is used to get the data from firebase once and only once.

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    hotels.add(snapshot.getValue(Business.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, HotelListActivity.class);
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
