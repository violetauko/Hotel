package com.ellah.ellahveehotels.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ellah.ellahveehotels.Constants;
import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.util.OnHotelSelectedListener;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelListActivity extends AppCompatActivity implements OnHotelSelectedListener {
private Integer mPosition;
    List<Business> mHotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mHotels = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_HOTELS));

                if (mPosition != null && mHotels != null) {
                    Intent intent = new Intent(this, HotelDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_HOTELS, Parcels.wrap(mHotels));
                    startActivity(intent);
                }

            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mHotels != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_HOTELS, Parcels.wrap(mHotels));
        }

    }

    @Override
    public void onHotelSelected(Integer position, List<Business> hotels) {
        mPosition = position;
        mHotels = hotels;


    }
}