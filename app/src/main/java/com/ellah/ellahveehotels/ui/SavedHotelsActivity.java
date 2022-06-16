package com.ellah.ellahveehotels.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ellah.ellahveehotels.Constants;
import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.adapters.FirebaseHotelListAdapter;
import com.ellah.ellahveehotels.adapters.FirebaseHotelViewHolder;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.util.ItemTouchHelperAdapter;
import com.ellah.ellahveehotels.util.OnStartDragListener;
import com.ellah.ellahveehotels.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedHotelsActivity extends AppCompatActivity {
    private DatabaseReference mHotelReference;//initialize the database reference
    private FirebaseRecyclerAdapter<Business, FirebaseHotelViewHolder> mFirebaseAdapter;//initialize the firebase adapter

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);//display correct layout
        ButterKnife.bind(this);

        mHotelReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOTELS);//set the database reference to the hotels child in the database
        setUpFirebaseAdapter();
        hideProgressBar();
        showHotels();
    }
    //We then create a method to set up the FirebaseAdapter by first creating a FirebaseRecyclerOptions object
// which is cast into the model class, we build the object by setting the query
// (or database reference) (by) passing in the database-reference and the Model class the objects will be parsed into
    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Business> options =
                new FirebaseRecyclerOptions.Builder<Business>()//
                        .setQuery(mHotelReference, Business.class)
                        .build();
//Inside of the onBindViewHolder() method,
// we call the bindRestaurant() method on our viewHolder
// to set the appropriate text and image with the given hotel.
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Business, FirebaseHotelViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseHotelViewHolder firebaseHotelViewHolder, int position, @NonNull Business hotel) {
                firebaseHotelViewHolder.bindHotel(hotel);
            }

            @NonNull
            @Override
            public FirebaseHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
                return new FirebaseHotelViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//set the layout manager for the recycler view
        mRecyclerView.setAdapter(mFirebaseAdapter);//set the adapter for the recycler view
    }
    //Finally, we need to clean up the FirebaseAdapter.
// When the activity is stops, we need to call onStop() on the adapter
// so that it can stop listening for changes in the Firebase database.
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showHotels() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}