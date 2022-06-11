package com.ellah.ellahveehotels.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellah.ellahveehotels.Constants;
import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.models.Category;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.hotelImageView)
    ImageView mImageLabel;
    @BindView(R.id.hotelNameTextView)
    TextView mNameLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.reviewButton) TextView mreviewButton;
    @BindView(R.id.emailTextView) TextView mEmail;
    @BindView(R.id.saveHotel)
    Button mSaveHotel;

    private Business mHotel;
    public HotelDetailFragment() {
        // Required empty public constructor
    }
    public static HotelDetailFragment newInstance(Business hotel) {
        HotelDetailFragment HotelDetailFragment = new HotelDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("hotel", Parcels.wrap(hotel));
        HotelDetailFragment.setArguments(args);
        return HotelDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHotel = Parcels.unwrap(getArguments().getParcelable("hotel"));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hotel_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mHotel.getImageUrl()).into(mImageLabel);

        List<String> categories = new ArrayList<>();

        for (Category category: mHotel.getCategories()) {
            categories.add(category.getTitle());
        }

        mNameLabel.setText(mHotel.getName());
        mRatingLabel.setText("Rating " + mHotel.getRating());
        mPhoneLabel.setText(mHotel.getPhone());
        mAddressLabel.setText(mHotel.getLocation().toString());


        mAddressLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mWebsiteLabel.setOnClickListener(this);
        mEmail.setOnClickListener(this);

        mSaveHotel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mHotel.getUrl()));
            startActivity(webIntent);
        }
        if (view == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mHotel.getPhone()));
            startActivity(phoneIntent);
        }
        if (view == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mHotel.getCoordinates().getLatitude()
                            + "," + mHotel.getCoordinates().getLongitude()
                            + "?q=(" + mHotel.getName() + ")"));
            startActivity(mapIntent);
        }
        if (view == mSaveHotel) {
            DatabaseReference ref = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_HOTELS);
            ref.push().setValue(mHotel);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    }
