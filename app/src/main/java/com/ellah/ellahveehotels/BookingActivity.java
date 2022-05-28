package com.ellah.ellahveehotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BookingActivity extends AppCompatActivity {
    private TextView mLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        mLocationTextView = findViewById(R.id.locationTextView);

        Intent intent = getIntent();
        String location = getIntent().getStringExtra("location");
        mLocationTextView.setText("Here are the hotels near " + location);
    }
}