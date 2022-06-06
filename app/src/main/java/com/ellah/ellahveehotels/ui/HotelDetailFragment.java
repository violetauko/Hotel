package com.ellah.ellahveehotels.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.models.Category;
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
public class HotelDetailFragment extends Fragment {
    @BindView(R.id.hotelImageView)
    ImageView mImageLabel;
    @BindView(R.id.hotelNameTextView)
    TextView mNameLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.priceButton) TextView mpriceButton;

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
        mRatingLabel.setText(Double.toString(mHotel.getRating()));
        mPhoneLabel.setText(mHotel.getPhone());
        mAddressLabel.setText(mHotel.getLocation().toString());
        return view;
    }
    }