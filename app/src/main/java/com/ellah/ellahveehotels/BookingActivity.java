package com.ellah.ellahveehotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BookingActivity extends AppCompatActivity {
    private TextView mLocationTextView;
    private Button mBook;
    private Button mBook1;
    private Button mBook2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        mLocationTextView = findViewById(R.id.locationTextView);
        mBook = findViewById(R.id.book);
        mBook.setOnClickListener(new View.OnClickListener() {//set the onclick listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, RoomsActivity.class);//create an intent to open the RoomsActivity
                startActivity(intent);//start the activity
            }
        });
        mBook1 = findViewById(R.id.book1);
        mBook1.setOnClickListener(new View.OnClickListener() {//set the onclick listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, RoomsActivity.class);//create an intent to open the RoomsActivity
                startActivity(intent);//start the activity
            }
        });
        mBook2 = findViewById(R.id.book2);
        mBook2.setOnClickListener(new View.OnClickListener() {//set the onclick listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, RoomsActivity.class);//create an intent to open the RoomsActivity
                startActivity(intent);//start the activity
            }
        });
        Intent intent = getIntent();
        String location = getIntent().getStringExtra("location");
        mLocationTextView.setText("Here are the hotels near " + location);
    }
}