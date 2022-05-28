package com.ellah.ellahveehotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mFindHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFindHotel = findViewById(R.id.findHotel);//find the button in the activity_main.xml
        mFindHotel.setOnClickListener(new View.OnClickListener() {//set the onclick listener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookingActivity.class);//create an intent to open the BookingActivity
                startActivity(intent);//start the activity
            }
        });
    }
}