package com.ellah.ellahveehotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.book)
    Button mBook;
    @BindView(R.id.book1)
    Button mBook1;
    @BindView(R.id.book2)
    Button mBook2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String location = getIntent().getStringExtra("location");
        mLocationTextView.setText("Here are the hotels near " + location);

        mBook.setOnClickListener(this);//set the onclick listener
        mBook1.setOnClickListener(this);
        mBook2.setOnClickListener(this);
    }//set the onclick listener

    @Override
    public void onClick(View v) {
        if (v == mBook) {
            Intent intent = new Intent(BookingActivity.this, RoomsActivity.class);//create an intent to open the RoomsActivity
            startActivity(intent);//start the activity
        }
        if (v == mBook1) {
            Intent intent = new Intent(BookingActivity.this, RoomsActivity.class);//create an intent to open the RoomsActivity
            startActivity(intent);//start the activity
        }
        if (v == mBook2) {
            Intent intent = new Intent(BookingActivity.this, RoomsActivity.class);//create an intent to open the RoomsActivity
            startActivity(intent);//start the activity
        }
    }


    }

