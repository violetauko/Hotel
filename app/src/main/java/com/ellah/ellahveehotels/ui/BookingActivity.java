package com.ellah.ellahveehotels.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.adapters.HotelListAdapter;
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
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HotelListAdapter mAdapter;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;


    public List<Business> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = getIntent().getStringExtra("location");


        BookingApi client = BookingClient.getClient();
        Call<HotelSearchResponse> call = client.getHotels(location, "hotels");
        call.enqueue(new Callback<HotelSearchResponse>() {
            @Override
            public void onResponse(Call<HotelSearchResponse> call, Response<HotelSearchResponse> response) {
                hideProgressBar();
if (response.isSuccessful()) {
                    hotels = response.body().getBusinesses();
                    mAdapter = new HotelListAdapter(BookingActivity.this, hotels);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookingActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);


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
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}