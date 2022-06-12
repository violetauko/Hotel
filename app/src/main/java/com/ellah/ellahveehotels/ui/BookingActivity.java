package com.ellah.ellahveehotels.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.ellah.ellahveehotels.Constants;
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

public class BookingActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
   private String mRecentAddress;

    private static final String TAG = BookingActivity.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HotelListAdapter mAdapter;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.saveHotel)
    Button mSaveHotel;

    public List<Business> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        mSaveHotel.setOnClickListener(this);

        Intent intent = getIntent();

       //Here, we retrieve our shared preferences from the preference manager,
        // pull data from it by calling getString() and providing the key that corresponds to the data we'd like to retrieve
        //The default null value will be returned if the getString() method is unable to find a value that corresponds to the key we provided.
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        String location = intent.getStringExtra("location");

        //String location = mRecentAddress;


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
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
    //In onCreateOptionsMenu() we inflate and bind our Views,
    // define our mSharedPreferences and mEditor member variables.
    // Also to retrieve a user’s search from the SearchView,
    // we must grab the action_search menu item from our new layout,
    // and use the MenuItemCompat.getActionView() method.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        return true;
    }
//onOptionsItemSelected() simply contains the line return super.onOptionsItemSelected(item);.
// This ensures that all functionality from the parent class (referred to here as super)
// will still apply despite us manually overriding portions of the menu's functionality.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onClick(View view) {
        if (view == mSaveHotel) {
            Intent intent = new Intent(BookingActivity.this, SavedHotelsActivity.class);
            startActivity(intent);
        }

    }
}