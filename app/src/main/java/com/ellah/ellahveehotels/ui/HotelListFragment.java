package com.ellah.ellahveehotels.ui;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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

public class HotelListFragment extends Fragment {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    public List<Business> hotels;
    private HotelListAdapter mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public HotelListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_hotel_list, container, false);
        ButterKnife.bind(this, view);

        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentAddress != null) {
            getHotels(mRecentAddress);
        }
        // Inflate the layout for this fragment
        return view;
    }
    private void getHotels(String location) {
        BookingApi client = BookingClient.getClient();

        Call<HotelSearchResponse> call = client.getHotels(location, "hotels");

        call.enqueue(new Callback<HotelSearchResponse>() {
            @Override
            public void onResponse(Call<HotelSearchResponse> call, Response<HotelSearchResponse> response) {

                if (response.isSuccessful()) {
                            hotels = response.body().getBusinesses();
                            mAdapter = new HotelListAdapter(getActivity(), hotels);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);

                            showHotels();
                } else {
                            showUnsuccessfulMessage();
                        }
                }

            @Override
            public void onFailure(Call<HotelSearchResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String location) {
                addToSharedPreferences(location);
                getHotels(location);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String location) {
                return false;
            }
        });
    }

    //onOptionsItemSelected() simply contains the line return super.onOptionsItemSelected(item);.
// This ensures that all functionality from the parent class (referred to here as super)
// will still apply despite us manually overriding portions of the menu's functionality.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private void showHotels() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void showUnsuccessfulMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
    }
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}

