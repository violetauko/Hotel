package com.ellah.ellahveehotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.locationEditText) EditText mLocationEditText;//bind the locationEditText
    @BindView(R.id.findHotel) Button mFindHotel;//bind the findHotel button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//bind the activity

        mFindHotel.setOnClickListener(new View.OnClickListener() {//set the onclick listener
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();//get the text from the edit text
                Intent intent = new Intent(MainActivity.this, BookingActivity.class);//create an intent to open the BookingActivity
                intent.putExtra("location", location);//put the location into the intent
                startActivity(intent);//start the activity
            }
        });
    }
}