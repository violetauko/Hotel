package com.ellah.ellahveehotels.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellah.ellahveehotels.Constants;
import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.adapters.FirebaseHotelListAdapter;
import com.ellah.ellahveehotels.adapters.FirebaseHotelViewHolder;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.util.OnStartDragListener;
import com.ellah.ellahveehotels.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedHotelsFragment extends Fragment implements OnStartDragListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private FirebaseHotelListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public SavedHotelsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_hotels, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }
    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_HOTELS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        FirebaseRecyclerOptions<Business> options =
                new FirebaseRecyclerOptions.Builder<Business>()
                        .setQuery(query, Business.class)
                        .build();
//Inside of the onBindViewHolder() method,
// we call the bindRestaurant() method on our viewHolder
// to set the appropriate text and image with the given hotel.
        mFirebaseAdapter = new FirebaseHotelListAdapter(options, query, this, getActivity());         
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}