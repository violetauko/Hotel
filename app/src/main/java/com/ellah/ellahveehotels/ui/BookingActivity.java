package com.ellah.ellahveehotels.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.models.HotelSearchResponse;
import com.ellah.ellahveehotels.network.BookingApi;
import com.ellah.ellahveehotels.network.BookingClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        Intent intent = getIntent();
        String location = getIntent().getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);

        BookingApi client = BookingClient.getClient();
        Call<HotelSearchResponse> call = client.getHotels(location, "hotels");
        call.enqueue(new Callback<HotelSearchResponse>() {
            @Override
            public void onResponse(Call<HotelSearchResponse> call, Response<HotelSearchResponse> response) {
                if (response.isSuccessful()) {
                    List<Business> hotelsList = response.body().getBusinesses();
                    String[] hotels = new String[hotelsList.size()];

                    for (int i = 0; i < hotels.length; i++) {
                        hotels[i] = hotelsList.get(i).getName();
                    }

                    ArrayAdapter adapter = new ArrayAdapter(BookingActivity.this, android.R.layout.simple_list_item_1, hotels);
                    mListView.setAdapter(adapter);

                    showHotels();
                } else {
                    showUnsuccessfulMessage();

                }
            }

            @Override
            public void onFailure(Call<HotelSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();

            }
        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showHotels() {
        mListView.setVisibility(View.VISIBLE);
        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}