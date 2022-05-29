package com.ellah.ellahveehotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.locationEditText) EditText mLocationEditText;//bind the locationEditText
    @BindView(R.id.findHotel) Button mFindHotel;//bind the findHotel button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//bind the activity

        mFindHotel.setOnClickListener(this);
    }//set the onclick listener
            @Override
            public void onClick(View v) {
                if (v == mFindHotel) {
                    String location = mLocationEditText.getText().toString();//get the location
                    if (location.isEmpty()) {//if the location is empty
                        Toast.makeText(MainActivity.this, "Please enter a location", Toast.LENGTH_SHORT).show();//show a toast
                    } else {
                        Intent intent = new Intent(MainActivity.this, BookingActivity.class);//create an intent to open the BookingActivity
                        intent.putExtra("location", location);//put the location in the intent
                        startActivity(intent);//start the activity
                    }
                }
            }
        }
