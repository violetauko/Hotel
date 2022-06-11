package com.ellah.ellahveehotels.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ellah.ellahveehotels.Constants;
import com.ellah.ellahveehotels.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.locationEditText) EditText mLocationEditText;//bind the locationEditText
    @BindView(R.id.findHotel) Button mFindHotel;//bind the findHotel button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//bind the activity

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();//get the editor
        mFindHotel.setOnClickListener(this);

    }//set the onclick listener
            @Override
            public void onClick(View v) {
                if (v == mFindHotel) {
                    String location = mLocationEditText.getText().toString();//get the location
                    addToSharedPreferences(location);//add the location to the shared preferences
                    Intent intent = new Intent(MainActivity.this, BookingActivity.class);//create an intent to go to the booking activity
                    startActivity(intent);//start the activity
                }
                }
               private void addToSharedPreferences(String location) {
                    mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();//
                }
            }
