package com.ellah.ellahveehotels.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    @BindView(R.id.emailTextView) TextView mEmail;
    @BindView(R.id.saveHotel)
    Button mSaveHotel;

    private Business mHotel;
    private List<Business> mHotels;
    private int mPosition;
    private String mSource;


//    private static final int REQUEST_IMAGE_CAPTURE = 111;
//    private static final int CAMERA_PERMISSION_REQUEST_CODE = 11;
//    private String currentPhotoPath;
//    private static final String TAG = "image creation value";


    public HotelDetailFragment() {
        // Required empty public constructor
    }
    public static HotelDetailFragment newInstance(List<Business> hotel,Integer position) {
        HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_HOTELS, Parcels.wrap(hotel));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
//        args.putString(Constants.KEY_SOURCE, source);

        hotelDetailFragment.setArguments(args);
        return hotelDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            mHotels = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_HOTELS));
            mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
            mHotel = mHotels.get(mPosition);
//        mSource = getArguments().getString(Constants.KEY_SOURCE);

        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        if (mSource.equals(Constants.SOURCE_SAVED)) {
//            inflater.inflate(R.menu.menu_photo, menu);
//        } else {
//            inflater.inflate(R.menu.menu_main, menu);
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_photo:
//                onLaunchCamera();
//            default:
//                break;
//        }
//        return false;
//    }
//
//    public void onLaunchCamera() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            mImageLabel.setImageBitmap(imageBitmap);
//            //      encodeBitmapAndSaveToFirebase(imageBitmap);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hotel_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get()
                .load(mHotel.getImageUrl())
                .into(mImageLabel);

        List<String> categories = new ArrayList<>();

        for (Category category: mHotel.getCategories()) {
            categories.add(category.getTitle());
        }

        mNameLabel.setText(mHotel.getName());
        mRatingLabel.setText(Double.toString(mHotel.getRating()) + "/5");
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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference ref = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_HOTELS)
                    .child(uid);
            DatabaseReference pushRef = ref.push();
            String pushId = pushRef.getKey();
            mHotel.setPushId(pushId);
            pushRef.setValue(mHotel);

            Intent intent = new Intent(getActivity(), SavedHotelsActivity.class);
            startActivity(intent);
        }
    }

    }
