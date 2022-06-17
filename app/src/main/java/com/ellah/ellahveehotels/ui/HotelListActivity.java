package com.ellah.ellahveehotels.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ellah.ellahveehotels.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        ButterKnife.bind(this);
    }

}